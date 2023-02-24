package com.campus.home;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
	//����	
	protected Connection con = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	protected CallableStatement cstmt = null;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "scott";
	String password = "tiger";
	
	 protected String sql = null;
	 
	 static { 
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
		 }catch(Exception e) {
			 System.out.println("����̺�ε� ���� �߻�....");
		 }
	 }
	
	 //DB 연결
	 public void getConnection() {
		 try {
			 con = DriverManager.getConnection(url, username, password);
		 }catch(Exception e) {
			 System.out.println("DB���� ���� �߻�..");
			 e.printStackTrace();
		 }
	 }
	 
	 //DB �ݱ�
	public void dbclose() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(cstmt != null) cstmt.close();
			if(con != null) con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
