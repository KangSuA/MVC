package com.campus.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Command클래스를 만들때 메소드명을 통일시키기위해 생성한 인터페이스
public interface CommandService {
	//     view파일명(jsp)을 리턴
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
