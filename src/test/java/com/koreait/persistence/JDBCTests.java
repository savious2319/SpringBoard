package com.koreait.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testConnection() {
		//try(statement) : 소괄호 안에 close를 필요로 하는 인스턴스를 작성하면 자동으로 close()를 실행해준다
		try(Connection conn =
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr")){
			
			log.info(conn);
			
		}catch(Exception e) {
			fail(e.getMessage());
		}
		
	}
}














