package com.campus.home;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ControllerAction")
public class ControllerAction extends HttpServlet {
	// mapping주소와 실행할 객체를 보관할 컬렉션
	Map<String,CommandService> map = new HashMap<String, CommandService>();
	
    public ControllerAction() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	// urlMapping.properties파일의 내용을 가져와서 HashMap에 key와 value로 셋팅
    	//1. web.xml에 initparam으로 property파일을 등록
    	String propertiesFileName = config.getInitParameter("proConfig");
    	//2. propertiesFileName의 내용을 객체에 저장
    	Properties propObject = new Properties();
    	try {
    		FileInputStream fis = new FileInputStream(propertiesFileName);
    		propObject.load(fis);
    	}catch(Exception e) {
    		System.out.println("property생성 예외발생>>>>>>>>>>>"+e.getMessage());
    	}
    	//3. propObject의 키 목록을 구하여 value를 객체로 만든 후 맵에 저장한다.
    	Enumeration keyList =  propObject.propertyNames();
    	try {
	    	while(keyList.hasMoreElements()) {
	    		String key = (String)keyList.nextElement(); //key값을 가져오기
	    		String className = propObject.getProperty(key); //key를 이용하여 value얻어오기
	    		System.out.println(key+"="+className);
	    		//====================================
	    		Class commandClass = Class.forName(className); // 문자열을 Class로 만들기
	    		
	    		// Class에서 객체를 가져와 인터페이스로 형변환한 후 Map추가하기
	    		CommandService service = (CommandService)commandClass.getDeclaredConstructors()[0].newInstance();
	    		map.put(key, service);
	    	}
    	}catch(Exception e) {
    		System.out.println("Map에 객체 추가하기 예외 발생..."+e.getMessage());
    	}
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// uri구하기
		String uri = req.getRequestURI(); //  /webMVC/index.do
		String ctx = req.getContextPath(); // /webMVC
		//접속한 주소 얻어오기
		String commandKey = uri.substring(ctx.length()); // /index.do
		
		//키를 이용하여 CommandService객체를 맵에 가져오기
		CommandService service = map.get(commandKey);
		String viewName = service.processStart(req, res);
		
		//뷰페이지로 이동하기
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewName);
		dispatcher.forward(req, res);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
