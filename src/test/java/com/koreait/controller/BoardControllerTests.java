package com.koreait.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //Servlet의 ServletContext를 이용하기 위함
@ContextConfiguration({
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class BoardControllerTests {
	
	//메모리에 Context영역을 할당해주는 ApplicationContext객체가 먼저 필요하다
	//Servlet을 사용하기 위한 궁극적인 목적으로 servlet-context.xml파일의 경로를 설정해 준 후
	//build된 서블릿을 MockMvc객체에 담아준다
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext wac; //WAS에 의존하지 않는다 -> build하면 servlet이 된다
	
	//가짜MVC
	//마치 브라우저에서 사용하는 것처럼 만들어서 Controller를 실행해 볼 수 있다
	private MockMvc mockMvc;
	
	@Before // @Before가 적용된 메소드는 모든 테스트 전에 매번 실행된다
	public void setup() {
							//Servlet을 만드는 친구
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); //서블릿을 항상 만들고 Test를 실행한다
	}
	
//	@Test
//	public void testRemove() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
//				.param("bno", "42")).andReturn().getModelAndView().getViewName();
//		
//		log.info(resultPage);
//	}

//	@Test
//	public void testModify() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
//				.param("bno", "22")
//				.param("title", "수정된 글 제목")
//				.param("content", "수정된 글 내용")
//				.param("writer", "brian")).andReturn().getModelAndView().getViewName();
//		
//		log.info(resultPage);
//	}
//	
//	@Test
//	public void testGet() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/read")
//				.param("bno", "42"))
//				.andReturn()
//				.getModelAndView()
//				.getModelMap());
//	}
//	
//	@Test
//	public void testRegister() throws Exception{
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
//				.param("title", "테스트 새 글 제목")
//				.param("content", "테스트 새 글 내용")
//				.param("writer", "hds1204")).andReturn().getModelAndView().getViewName();
//		
//		log.info(resultPage);
//	}
	
	
	@Test
	public void testList() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "1")
				.param("amount", "10")
				.param("type", "TCW")
				.param("keyword", "백성민")) //GET방식으로 URL 호출
		.andReturn()	//반환된 결과를 이용해서
		.getModelAndView()		//Model에 어떤 데이터가 담겨 있는지
		.getModelMap());	//Map형식으로 확인
	}
	
//	@Test
//	public void testList() throws Exception{
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
//				.param("pageNum", "2")
//				.param("amount", "10")) //GET방식으로 URL 호출
//				.andReturn()	//반환된 결과를 이용해서
//				.getModelAndView()		//Model에 어떤 데이터가 담겨 있는지
//				.getModelMap());	//Map형식으로 확인
//	}
	
	
	
	
}




