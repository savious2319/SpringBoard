package com.koreait.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.domain.BoardVO;
import com.koreait.domain.Criteria;
import com.koreait.domain.FilesVO;
import com.koreait.mapper.BoardMapper;
import com.koreait.mapper.FilesMapper;
import com.koreait.util.FileUtils;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

//주입은 반드시 가장 위에서부터 아래로 내려오듯이 주입해야 하기 때문에
//구현한 클래스가 아닌 틀을 제공하는 인터페이스를 통해서 주입한다.

@Log4j
@Service	// 서비스 객체임을 표시
@AllArgsConstructor	// 모든 파라미터를 이용하는 생성자를 선언
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private FilesMapper f_mapper;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Override
	public void register(BoardVO board) {
		log.info("register board......" + board);
		
		mapper.insertSelectKey_bno(board);
		
	}

	//조회 작업의 구현과 테스트
	@Override
	public BoardVO get(Long bno) {
		log.info("get.........");
		
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify......");
		
		if(mapper.update(board) == 1) {
			return true;
		}else {
			return false;
		}
		
//		return mapper.update(board) == 1;
		
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove.....");
		
		if(mapper.delete(bno) == 1) {
			return true;
		}else {
			return false;
		}
		
//		return mapper.update(board) == 1;
	}
	
	//목록작업의 구현과 테스트
	@Override
	public List<BoardVO> getList(Criteria cri) {
		
		log.info("getList......" + cri);
		
		return mapper.getListWithPaging(cri);
		
	}
	
	//게시글 전체 개수
	@Override
	public int getTotal(Criteria cri) {
		log.info("getTotal");
		
		return mapper.getTotal(cri);
	}
	
	@Override
	public List<FilesVO> getPics(Long bno) {
		log.info("getPics");
		
		return f_mapper.getDetail(bno);
		
	}
	
	@Override
	public void savePics(Long bno, MultipartHttpServletRequest multi) {
		

        try {
			//ArrayList<HashMap<String, Object>> list = fileUtils.parseInsertFileInfo(bno, multi); 
			ArrayList<FilesVO> list = fileUtils.parseInsertFileInfo(bno, multi); 

			for(int i=0; i < list.size(); i++){ 
//				System.out.println("들어옴" + i+1);
				
//				list.forEach(files->System.out.println();(files.getFile_name()));
//				
				f_mapper.insertFile(list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void deletePics(Long bno) {
		f_mapper.deleteFile(bno);
	}

}



