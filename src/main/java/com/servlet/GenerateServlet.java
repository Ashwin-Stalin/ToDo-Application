package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.User;
import com.model.UserList;

public class GenerateServlet extends HttpServlet {
	private UserList userList = UserList.getInstance();
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try(PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession(false);  
		    if(session==null){  
		    	response.sendRedirect("login-page");  
		    }else{
		    	String apiKey = this.randomString(32);
		    	String name = (String)session.getAttribute("name"); 
		    	for(User user : this.userList.getUserList()) {
		    		if(user.getUsername().contentEquals(name))
		    			user.setApiKey(apiKey);
		    	}
		    	response.sendRedirect("profile");
		    }
		
		}catch(IOException ioexception) {
			System.out.println("catch Io exception : " + ioexception.getMessage());
			ioexception.printStackTrace();
		}
	}
	private String randomString(int num) {
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < num) {
            int index = (int) (rnd.nextFloat() * alphaNumeric.length());
            result.append(alphaNumeric.charAt(index));
        }
        return result.toString();

    }
}
