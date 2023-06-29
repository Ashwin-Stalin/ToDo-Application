package com.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();  
			session.invalidate();
			response.sendRedirect("login-page");
		}catch(IOException ioexception) {
			System.out.println("catch Io exception : " + ioexception.getMessage());
			ioexception.printStackTrace();
		}
	}
}
