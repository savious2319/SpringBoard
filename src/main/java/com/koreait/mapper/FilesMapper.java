package com.koreait.mapper;


import java.util.HashMap;
import java.util.List;


import com.koreait.domain.FilesVO;

public interface FilesMapper {
	
	
	public List<FilesVO> getDetail(Long bno);
	
	public void insertFile(FilesVO files);
	//public void insertFile(HashMap<String, Object> data);
	
	public void deleteFile(Long bno);
	
}
