package com.koreait.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koreait.domain.BoardVO;
import com.koreait.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	
//	@Test
//	public void testGetTotal() {
//		log.info("TOTAL COUNT : " + service.getTotal(new Criteria(1, 10)));
//	}
//	@Test
//	public void testRemove() {
//		Long bno = 41L;
//
//		// 실행 전 존재하는 번호인지 확인할 것
//		if (service.get(bno) != null) {
//			
//			if (service.remove(bno)) {
//				log.info("삭제 성공");
//			} else {
//				log.info("삭제 실패");
//			}
//		} else {
//			log.info("***************No such board***************");
//		}
//	}
	
//	@Test
//	public void testRemove() {
//		Long bno = 1L;
//		BoardVO board = service.get(bno);
//		
//		if(board == null) {
//			return;
//		}
//		
//		log.info("REMOVE RESULT : " + service.remove(bno));
//	}

//	@Test
//	public void testModify() {
//		BoardVO board = new BoardVO();
//		Long bno = 22L;
//		
//		// 실행 전 존재하는 번호인지 확인할 것
//		if(service.get(bno) != null) {
//			board.setBno(bno);
//			board.setTitle("내가 수정한 제목");
//			board.setContent("내가 수정한 내용");
//			board.setWriter("Brian2319");
//			
//			if(service.modify(board)) {
//				log.info("업데이트 성공");
//			}else {
//				log.info("업데이트 실패");
//			}
//		}else {
//			log.info("***************No such board***************");
//		}
//	}

//	@Test
//	public void testModify() {
//		BoardVO board = service.get(1L);
////		assertNotNull(board);
//		if(board == null) {
//			return;
//		}
//		board.setTitle("제목 수정합니다.");
//		log.info("MODIFY RESULT : " + service.modify(board));
//	}
	
	
//	@Test
//	public void testGet() {
//		Long bno = 22L;
//		
//		// 실행 전 존재하는 번호인지 확인할 것
//		if(service.get(bno) != null) {
//			
//			log.info(service.get(bno));
//			
//		}else {
//			log.info("***************No such board***************");
//		}
//	}

//	@Test
//	public void testGetList() {
//		
//		/*for (BoardVO board : service.getList()) {
//			
//		}*/
//		
//		//람다식 forEach를 사용하자
//		
//				//Collection.forEach(선언부 -> 사용부)
//		service.getList().forEach(board->log.info(board));
//	}
	
//	@Test
//	public void testGetList() {
//		
//		service.getList(new Criteria(2, 10)).forEach(board->log.info(board));
//	}
//
//	@Test
//	public void testRegister() {
//		BoardVO b_vo = new BoardVO();
//		b_vo.setTitle("새로 작성하는 글5");
//		b_vo.setContent("새로 작성하는 내용5");
//		b_vo.setWriter("hds1204");
//		
//		service.register(b_vo);
//		
//		log.info("생성된 게시물의 번호 : " + b_vo.getBno());
//	}
//
//	@Test
//	public void testExist() {
//		assertNotNull(service);
//		log.info("SERVICE : "+service);
//	}

}
