package com.koreait.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.domain.BoardVO;
import com.koreait.domain.Criteria;
import com.koreait.domain.FilesVO;

public interface BoardService {
	/*
	 * 메소드 설계시 메소드 이름은 최대한 현실적인 로직의 이름을 붙여준다
	 */
	
	
	//게시물 등록
	public void register(BoardVO board);
	
	//특정 게시물 가져오기
	public BoardVO get(Long bno);
	
	//게시물 수정
	public boolean modify(BoardVO board);
	
	//게시물 삭제
	public boolean remove(Long bno);
	
	//전체 게시물 가져오기
//	public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri);
	
	//전체 게시물 개수 가져오기
	public int getTotal(Criteria cri);
	
	//사진 정보 가져오기
	public List<FilesVO> getPics(Long bno);
	
	//사진 저장하기
	public void savePics(Long bno, MultipartHttpServletRequest multi);
	
	//사진 삭제하기
	public void deletePics(Long bno);
	
	
}
