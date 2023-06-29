package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginPageServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try(PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession(false);  
		    if(session!=null){  
		    	response.sendRedirect("profile");  
		    }else{
			    out.println("<html>");
			    out.println("<body>");
			    out.println("<h1>Login Page </h1>");
			    out.println("<br>");
			    out.println("<form action=\"login\" method=\"post\">");
			    out.println("UserName: <input type=\"text\" name=\"uname\" required><br><br>");
			    out.println("Password: <input type=\"password\" name=\"pass\" required><br><br>");
			    out.println("<input type=\"submit\" value=\"LOGIN\">");
			    out.println("</form>");
			    out.println("</body>");
			    out.println("</html>");
		    }
		}catch(IOException ioexception) {
			System.out.println("catch Io exception : " + ioexception.getMessage());
			ioexception.printStackTrace();
		}
	    
	}
}
