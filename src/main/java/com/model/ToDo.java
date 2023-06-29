package com.model;

public class ToDo {
	private int id;
	private String task = null;
	
	public ToDo(String task){
		this.task = task;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTask() {
		return this.task;
	}
	
	public void setTask(String task) {
		this.task = task;
	}
}
