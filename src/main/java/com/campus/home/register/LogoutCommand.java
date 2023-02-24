package com.campus.home.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campus.home.CommandService;

public class LogoutCommand implements CommandService {

	@Override
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//세션 제거 -> 홈으로 이동
		req.getSession().invalidate();
		return "index.jsp";
	}

}
