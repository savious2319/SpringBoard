package com.koreait.mapper;

import java.util.HashMap;

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
public class BoardMapperTests {

	@Setter(onMethod_ = { @Autowired })
	private BoardMapper mapper;
	
	@Setter(onMethod_ = { @Autowired })
	private FilesMapper f_mapper;
	
//	@Test
//	public void testInsert1() {
//		HashMap<String, Object> data = new HashMap<>();
//		data.put("bno", 287L);
//		data.put("program_language", "java");
//		k_mapper.insert1(data);
//	}
	
//	@Test
//	public void testInsert2() {
//		KoreaitVO kvo = new KoreaitVO();
//		kvo.setBno(286L);
//		kvo.setProgram_language("c");
//		k_mapper.insert2(kvo);
//	}
	
//	@Test
//	public void testSelect() {
//		//k_mapper.select(284L).forEach(koreait->log.info(koreait.getProgram_language()));
//		log.info(k_mapper.select(241L));
//	}
	
	@Test
	public void testGetPic() {
//		f_mapper.getDetail(307L).forEach(files->log.info(files.getFile_name()));
		log.info(f_mapper.getDetail(309L));
	}
	
//	@Test
//	public void testGetTotal() {
//		log.info("TOTAL COUNT : " + mapper.getTotal(new Criteria(1, 10, "TCW", "백성민")));
//	}
	
//	@Test
//	public void testGetListWithPaging() {
////		Criteria cri = new Criteria(2, 10);
//		Criteria cri = new Criteria(2, 30);
//		mapper.getListWithPaging(cri).forEach(board->log.info(board.getBno()));
//		
//	}
//	@Test
//	public void testInsert() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로 작성한 글 제목");
//		board.setContent("새로 작성한 글 내용");
//		board.setWriter("newbie");
//		
//		mapper.insert(board);
//		
//		//결과 작업은 완료되지만 현재 추가된 데이터의 PK값을 알 수 없다.
//		//bno가 null로 표기
//		log.info(board);
//	}

//	@Test
//	public void testInsertSelectKey_bno() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로 작성한 글 제목2");
//		board.setContent("새로 작성한 글 내용2");
//		board.setWriter("newbie");
//		
//		mapper.insertSelectKey_bno(board);
//		
//		//PK값을 미리 SQL을 통해서 처리해 두고 지정한 이름으로 결과를 보관하는 방식.
//		//SQL을 한 번 더 실행해야 하는 부담이 덜고 자동으로 추가되는 PK값을 확인해야 하는 상황에서는
//		//유용하게 사용될 수 있다.
//		
//		log.info(board);
//	}

//	@Test
//	public void testRead() {
//		BoardVO board = mapper.read(307L);
//		
//		log.info(board);
//	}

//	@Test
//	public void testDelete() {
//		log.info("DELETE COUNT : "+ mapper.delete(23L));
//	}

//	@Test
//	public void testUpdate() {
//		BoardVO board = new BoardVO();
//		Long bno = 1L;
//		// 실행 전 존재하는 번호인지 확인할 것
//
//		board = mapper.read(bno);
//
////		if (board != null) {
//		if(mapper.read(bno) != null) {
//			board.setBno(bno);
//			board.setTitle("수정된 제목");
//			board.setContent("수정된 내용");
//			board.setWriter("user00");
//
//			int count = mapper.update(board);
//			log.info("UPDATE COUNT : " + count);
//			
//		}else {
//			log.info("***************No such board***************");
//		}
//	}

//	@Test
//	public void testGetList() {
//		mapper.getList().forEach(board->log.info(board));
//	}

}
