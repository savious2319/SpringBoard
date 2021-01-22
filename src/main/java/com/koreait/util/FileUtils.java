package com.koreait.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.domain.FilesVO;

@Component("fileUtils")
public class FileUtils {
	//private static final String filePath = "D:/GB_0900_07_BSM/Spring/workspace_practice/SpringBoard/src/main/webapp/WEB-INF/views/upload/";
	private static final String filePath = "C:/GB_0900_07_BSM/Spring/board_practice/SpringBoard/src/main/webapp/WEB-INF/views/upload/";

	public ArrayList<FilesVO> parseInsertFileInfo(Long bno, MultipartHttpServletRequest multi) {

		Iterator<String> iterator = multi.getFileNames();

		FilesVO files = null;

		ArrayList<FilesVO> list = new ArrayList<FilesVO>();

		//스프링에서 쓰는 파일업로드 인터페이스
		MultipartFile multipartFile = null;

		File file = new File(filePath);

		while (iterator.hasNext()) {
			
			//반복할때마다 files 객체를 새롭게 만든다
			files = new FilesVO();
			
			multipartFile = multi.getFile(iterator.next());
			if (!multipartFile.isEmpty()) {
				
				//업로드 한 파일의 실제 이름을 구한다.
				String originalFileName = multipartFile.getOriginalFilename();

				String savedName = changeFileName(originalFileName);

				System.out.println("savedName : " + savedName);

				file = new File(filePath + savedName);

				try {
					//업로드 한 파일 데이터를 지정한 경로에 저장한다.
					multipartFile.transferTo(file);

				} catch (Exception e) {
					e.printStackTrace();
				}

				files.setBno(bno);
				files.setFileName(savedName);

				System.out.println("files.getFile_name() : " + files.getFileName());
				
				list.add(files);
			}
		}
		System.out.println(list);
		return list;
	}
	
	//업로드된 파일명 중복을 막기위해 파일명 앞에 유일한 식발자를 붙여준다
	public String changeFileName(String originalFileName) {
		UUID uuid = UUID.randomUUID();

		String savedName = uuid.toString() + "_" + originalFileName;

		return savedName;
	}

}
