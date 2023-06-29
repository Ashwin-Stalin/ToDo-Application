package com.model;

public class User {
	private String userName = null;
	private String password = null;
	private String apiKey = null;
	public ToDoList toDoList = new ToDoList();
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	public String getUsername() {
		return this.userName;
	}
	public String getPassword() {
		return this.password;
	}
	public String getApiKey() {
		return this.apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
