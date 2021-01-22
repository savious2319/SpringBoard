package com.koreait.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.koreait.domain.BoardVO;
import com.koreait.domain.Criteria;

public interface BoardMapper {
//	@Select("SELECT * FROM TBL_BOARD WHERE BNO > 0")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey_bno(BoardVO board);

	public BoardVO read(Long bno);
	
	//게시물이 삭제되었다면 1 이상의 값이 리턴되고,
	//없는 게시물이라면 0이 리턴된다.
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public int getTotal(Criteria cri);
}
