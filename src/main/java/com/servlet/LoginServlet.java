package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;
import com.model.UserList;

public class LoginServlet extends HttpServlet {
	private UserList userList = UserList.getInstance();
	
	private boolean authenticateUser(String uname, String pass) {
		for(User user : this.userList.getUserList()) {
			if(user.getUsername().contentEquals(uname)) {
				if(user.getPassword().contentEquals(pass)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try(PrintWriter out = response.getWriter()) {
			String uname = request.getParameter("uname");
			String pass = request.getParameter("pass");
			if(this.authenticateUser(uname, pass)) {
				HttpSession session=request.getSession();  
		        session.setAttribute("name",uname);  
				response.sendRedirect("profile");
			}else {
				response.setStatus(401);
				out.println("<font color=red>Wrong Credentials</font>");
				request.getRequestDispatcher("login-page").include(request, response);
			}
		}catch(IOException ioexception) {
			ioexception.printStackTrace();
		}catch(ServletException servletException) {
			servletException.printStackTrace();
		}
	}
}
