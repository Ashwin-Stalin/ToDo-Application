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

public class ProfileServlet extends HttpServlet {
	private UserList userList = UserList.getInstance();
	public void service(HttpServletRequest request, HttpServletResponse response) {
		String apiKey = null;
		String name = null;
		try(PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession(false);  
		    if(session==null){  
		    	response.sendRedirect("login-page");
		    }else{
		    	name = (String)session.getAttribute("name"); 
		    	for(User user : this.userList.getUserList()) {
		    		if(user.getUsername().contentEquals(name)) 
		    			apiKey = user.getApiKey();
		    	}
			    out.println("<html>");
			    out.println("<body>");
			    request.getRequestDispatcher("links.html").include(request, response); 
			    out.println("<br>");
			    out.println("<h1>Welcome , " + name + "</h1>");
			    out.println("Your api-key : " + apiKey);
			    out.println("<form action=\"generate\" method=\"post\">");
			    out.println("Generate API Key : <input type=\"submit\" value=\"GENERATE\">");
			    out.println("</form>");
			    
			    out.println("</body>");
			    out.println("</html>");
		    }
		
		}catch(IOException ioexception) {
			System.out.println("catch Io exception : " + ioexception.getMessage());
			ioexception.printStackTrace();
		}catch(ServletException servletException) {
			System.out.println("catch servlet exception : " + servletException.getMessage());
			servletException.printStackTrace();
		}
	    
	}
}
