import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.ToDo;
import com.model.User;
import com.model.UserList;
import com.model.Respond;
import com.google.gson.Gson;

public class controlServlet extends HttpServlet {
	/*
	    1. GET /tasks/ - to get all tasks
		2. GET /tasks/{id} - to get specific task by id of task
		3. POST /tasks/ - to create new task
		4. PUT /tasks/{id} - to update a specific task by id of task
		5. DELETE /tasks/{id} - to delete a specific task by id of task
	 */
	private UserList userList = UserList.getInstance();
	private User authenticate(HttpServletRequest request) {
		try {
			String apiKey = request.getParameter("api_key");
			for(User user : this.userList.getUserList()) {
				if(user.getApiKey().contentEquals(apiKey)) {
					return user;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private void respond(HttpServletResponse response, int statusCode, String message) {
		try(PrintWriter out = response.getWriter()){
			Respond res = new Respond();
			Gson gson = new Gson();
			response.setStatus(statusCode);
			res.setMessage(message);
			String jsonResponse = gson.toJson(res);
			out.println(jsonResponse);
		}catch(IOException ioexception) {
			ioexception.printStackTrace();
		}
	}
	private void respond(HttpServletResponse response, int statusCode, ToDo todo) {
		try(PrintWriter out = response.getWriter()){
			Gson gson = new Gson();
			
			response.setStatus(statusCode);
			String jsonResponse = gson.toJson(todo);
			out.println(jsonResponse);
		}catch(IOException ioexception) {
			ioexception.printStackTrace();
		}
	}
	private void respond(HttpServletResponse response, int statusCode, List<ToDo> toDoList) {
		try(PrintWriter out = response.getWriter()){
			Gson gson = new Gson();
			
			response.setStatus(statusCode);
			String jsonResponse = gson.toJson(toDoList);
			out.println(jsonResponse);
		}catch(IOException ioexception) {
			ioexception.printStackTrace();
		}
	}
	private ToDo getTodo (HttpServletRequest request) {
		ToDo todo = null;
		try {
			Gson gson = new Gson();
			todo = gson.fromJson(request.getReader(), ToDo.class);
		}catch(IOException ioexception) {
			ioexception.printStackTrace();
		}
		return todo;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
			User user = this.authenticate(request);
			response.setContentType("application/json");
			if(user != null) {
				String pathInfo = request.getPathInfo();
				if(pathInfo == null || pathInfo.contentEquals("/")) {
					this.respond(response, 200, user.toDoList.getToDoList());
				}else {
					String[] pathSplits = pathInfo.split("/");
					try {
						int id = Integer.parseInt(pathSplits[1]);
						if(user.toDoList.isId(id)){
							ToDo todo = user.toDoList.getToDoById(id);
							this.respond(response, 200, todo);
						}else {
							this.respond(response, 404, "No Such Id Exit!");
						}
					}catch(Exception e) {
						this.respond(response, 415, "UnSupported Type!");
					}
				}
			}else {
				this.respond(response, 401, "Unauthorized!");
			}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response){
			User user = this.authenticate(request);
			response.setContentType("application/json");
			if(user!=null) {
				ToDo todo = this.getTodo(request);
				
				response.setStatus(200);
				if(todo == null || todo.getTask() == null) {
					this.respond(response, 400, "Invalid Requset!");
				}else {
					user.toDoList.addToDo(todo);
					this.respond(response, 200, "Added Successfully!");
					
				}
			}else {
				this.respond(response, 401, "Unauthorized!");
			}
	}
	public void doPut(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("application/json");	
		User user = this.authenticate(request);
			if(user != null) {
				String pathInfo = request.getPathInfo();
				if(pathInfo == null || pathInfo.contentEquals("/")) {
					this.respond(response, 400, "Invalid Request!");
				}else {
					String[] pathSplits = pathInfo.split("/");
					try {
						int id = Integer.parseInt(pathSplits[1]);
						if(user.toDoList.isId(id)){
							ToDo todo = this.getTodo(request);
							if(todo == null || todo.getTask() == null) {
								this.respond(response, 400, "Invalid Request!");
							}else {
								user.toDoList.updateToDoById(id, todo.getTask());
								this.respond(response, 200, "Updated Successfully!");
							}
						}else {
							this.respond(response, 404, "No Such Id Exist!");
						}
					}catch(Exception e) {
						this.respond(response, 415, "UnSupported Type!");
					}
				}
			}else {
				this.respond(response, 401, "Unauthorized!");
			}
	}
	public void doDelete(HttpServletRequest request, HttpServletResponse response) {
			User user = this.authenticate(request);
			response.setContentType("application/json");
			if(user != null) {
				String pathInfo = request.getPathInfo();
				if(pathInfo == null || pathInfo.contentEquals("/")) {
					this.respond(response, 400, "Invalid Request!");
				}else {
					String[] pathSplits = pathInfo.split("/");
					try {
						int id = Integer.parseInt(pathSplits[1]);
						if(user.toDoList.isId(id)){
							user.toDoList.deleteToDoById(id);
							this.respond(response, 200, "Deleted Successfully!");
						}else {
							this.respond(response, 404, "No Such Id Exist!");
						}
					}catch(Exception e) {
						this.respond(response, 415, "UnSupported Type!");
					}
				}
			}else {
				this.respond(response, 401, "Unauthorized!");
			}
	}
	
}
