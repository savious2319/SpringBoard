package com.koreait.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.domain.BoardVO;
import com.koreait.domain.Criteria;
import com.koreait.domain.FilesVO;
import com.koreait.domain.PageDTO;
import com.koreait.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

	// DI (Dependency Injection 의존성 주입)
	private BoardService service;

	@GetMapping("/elements")
	public void elements() {
	}

	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list");
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));

	}

	// register GET방식 컨트롤러 작성
	@GetMapping("/register")
	public void register(@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("register");

	}

	// 1. 어떻게 redirect로 전송할 것인가(default는 forward)
	// 2. 어떻게 redirect로 데이터를 응답페이지로 전달할 것인가?
	@PostMapping("/register")
	// public String register(BoardVO board, RedirectAttributes rttr,
	// @RequestParam("file") ArrayList<MultipartFile> files) {
	public String register(BoardVO board, RedirectAttributes rttr, MultipartHttpServletRequest multi) {

		log.info("register board : " + board);
		log.info("register multi: " + multi);

		service.register(board);

		service.savePics(board.getBno(), multi);

		// 컨트롤러에서 ArrayList<MultipartFile> files로 전달받기
		// file.getOriginalFilename() 출력

		// files.forEach(file->log.info(file.getOriginalFilename()));

		// Redirect로 전송할 때에는 데이터가 유실되기 때문에
		// Session의 flash부분에 값을 저장해 놓은 후
		// 새로운 페이지가 시작될 때 Model 전달자에게 저장시켜준다
		// 저장된 후 이전의 데이터는 소멸된다.
		rttr.addFlashAttribute("result", board.getBno());

//		model.addAttribute("result", board.getBno());

		// 'redirect' 접두어를 사용하면 스프링MVC가 내부적으로
		// response.sendRedirect()를 처리해준다
		return "redirect:/board/list";

	}

	// 조회 처리와 테스트
	// 데이터 전달자에는 전달받은 bno에 대한 게시글 정보를 담아준다
	@GetMapping({ "/get", "/modify" })
	// @RequestParam을 사용하지 않아도 되지만 명시적으로 지정하여 발생할 수 있는 예외를 막아준다
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("get");

		model.addAttribute("board", service.get(bno));

		log.info("bno : " + bno);

		List<FilesVO> files = service.getPics(bno);
		for (FilesVO file : files) {
			log.info(file);
		}

//		if (files.isEmpty()) {
//			model.addAttribute("files", null);
//		} else {
//			model.addAttribute("files", service.getPics(bno));
//		}
		
		model.addAttribute("files", service.getPics(bno));
	}

	// 수정페이지로 이동하는 컨트롤러 선언 ↑ 위에 메소드에 추가한다. 반복적으로 다시 작성할 필요가 없다
//	@GetMapping("/modify")
//	public void modify(@RequestParam("bno") Long bno, Model model) {
//		log.info("modify");
//		
//		model.addAttribute("board", bno);
//	}

	// 수정 성공시 result라는 KEY에 success를 넣어 결과 페이지로 전달
	// POST방식
	// redirect 전송
	@PostMapping("/modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr, MultipartHttpServletRequest multi) {
		log.info("modify : " + board);

		//String filePath = "D:/GB_0900_07_BSM/Spring/workspace_practice/SpringBoard/src/main/webapp/WEB-INF/views/upload";
		String filePath = "C:/GB_0900_07_BSM/Spring/board_practice/SpringBoard/src/main/webapp/WEB-INF/views/upload";

		List<FilesVO> filesList = service.getPics(board.getBno());

		log.info("filesList : " + filesList.isEmpty());

		if (!filesList.isEmpty()) {
			for (FilesVO file : filesList) {
				File f = new File(filePath + "/" + file.getFileName());
				log.info("f.exits() : " + f.exists());
				if (f.exists()) {
					f.delete();
					log.info("파일 삭제성공");
				}
			}
		}
		service.deletePics(board.getBno());

		if (service.modify(board)) {
			// 리다이렉트 이후에도 Model 객체에 담겨 있다(Session, Flash 사용)
			rttr.addFlashAttribute("result", "success"); // URL에 안 보인다
		}

		service.savePics(board.getBno(), multi);

		// 리다이렉트 이후에도 URL에 남아있다(GET방식 이용) URL에 보인다
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/board/list" + cri.getListLink();
	}

	// 삭제 처리
	// POST방식
	// 게시판 번호 받기
	// 서비스에 있는 적절한 메소드 호출
	// FlashAttribute 사용
	// 성공 시 result에 success담아서 view로 전달
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
		log.info("remove : " + bno);

		//String filePath = "D:/GB_0900_07_BSM/Spring/workspace_practice/SpringBoard/src/main/webapp/WEB-INF/views/upload";
		String filePath = "C:/GB_0900_07_BSM/Spring/board_practice/SpringBoard/src/main/webapp/WEB-INF/views/upload";

		List<FilesVO> filesList = service.getPics(bno);

		log.info("filesList : " + filesList.isEmpty());

		if (!filesList.isEmpty()) {
			for (FilesVO file : filesList) {
				File f = new File(filePath + "/" + file.getFileName());
				log.info("f.exits() : " + f.exists());
				if (f.exists()) {
					f.delete();
					log.info("파일 삭제성공");
				}
			}
		}

		service.deletePics(bno);

		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());

		return "redirect:/board/list" + cri.getListLink();
	}

	@GetMapping("/download")
	public void fileDownalod(@RequestParam("file_name") String fileName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		//String filePath = "D:/GB_0900_07_BSM/Spring/workspace_practice/SpringBoard/src/main/webapp/WEB-INF/views/upload";
		String filePath = "C:/GB_0900_07_BSM/Spring/board_practice/SpringBoard/src/main/webapp/WEB-INF/views/upload";

		// OutputStream과 같은 Stream을 사용하기 때문에 충돌을 방지하기 위해 객체 생성
		PrintWriter out = response.getWriter();

		InputStream in = null;
		OutputStream os = null;
		File file = null;
		boolean check = true;
		String client = "";

		// 파일을 읽어서 스트림에 담기
		try {
			try {
				// 해당 경로에 있는 사용자가 요청한 파일 정보
				file = new File(filePath, fileName);

				// 해당 파일을 byte로 읽어온다
				in = new FileInputStream(file); // 파일을 읽어 온다
			} catch (FileNotFoundException fnfe) {
				log.info(fnfe + "\nfileDownload() 오류");
				// 해당 경로에 파일이 존재하지 않으면 check에 false 담기
				check = false;
			}

			// "User-Agent" KEY값을 헤더에 전달하여 브라우저 정보 가져오기
			client = request.getHeader("User-Agent");

			// 파일 다운로드 헤더 지정
			response.reset(); // 응답객체 초기화
			response.setContentType("application/octet-stream"); // 응답할 컨텐트 타입 설정
			response.setHeader("Content-Description", "JSP Generated Data"); // 컨텐트 설명 추가

			if (check) {
				// 해당 파일의 인코딩 설정
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");

				// Microsoft
				// IE(Internet Explorer)
				if (client.indexOf("MSIE") != -1) {
					response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

				} else {// 그 외의 브라우저에서 사용자가 요청했을 때
					// 파일 이름 앞 뒤로 큰 따옴표를 작성해준다
					response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
					// 그 외 브라우저일 경구 컨텐트 타입을 다시 한 번 설정해준다
					response.setHeader("Content-Type", "application/octet-stream;charset=UTF-8");
				}

				// 컨텐트 길이 설정
				response.setHeader("Content-Length", "" + file.length());

				// 공유하는 공간을 비워준다
				out.flush(); // PrintWrite를 비워준다

				// 다운로드 준비과정
				os = response.getOutputStream();

				// 해당 파일의 길이만큼 byte배열을 선언한다
				byte b[] = new byte[(int) file.length()];

				int len = 0;

				// 파일의 내용을 반복하여 읽어 온 후 더 이상 읽어올 내용이 없을 때까지 반복한다
				while ((len = in.read(b)) > 0) {
					// 읽어온 내용 출력
					os.write(b, 0, len);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}

	}

}
