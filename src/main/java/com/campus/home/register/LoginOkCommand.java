package com.campus.home.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.campus.home.CommandService;

public class LoginOkCommand implements CommandService {

	@Override
	public String processStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		RegisterDTO dto = new RegisterDTO();
		dto.setUserid(req.getParameter("userid"));
		dto.setUserpwd(req.getParameter("userpwd"));
		
		RegisterDAO dao = new RegisterDAO();
		RegisterDTO dtoResult = dao.login(dto);
		
		String viewname = null;
		if(dtoResult.getUserid()!=null && dtoResult.getUsername()!=null) {//로그인성공 -> 세션에 로그인 정보기록 -> "index.jsp"
			HttpSession session = req.getSession();
			session.setAttribute("logId", dtoResult.getUserid());
			session.setAttribute("logName", dtoResult.getUsername());
			
			viewname = "index.jsp";//로그인성공뷰파일명
		} else{//로그인실패 -> "loginForm.jsp"
			viewname = "register/loginForm.jsp";
		}
		return viewname;
	}

}
