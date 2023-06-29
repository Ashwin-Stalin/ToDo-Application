package com.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

public class ToDoList {
	private List<ToDo> ToDoList = new ArrayList<>();
	private AtomicInteger toDoId = new AtomicInteger(0);
	public void addToDo(ToDo todo) {
		todo.setId(toDoId.getAndIncrement());
		this.ToDoList.add(todo);
	}
	public List<ToDo> getToDoList() {
		return this.ToDoList;
	}
	public ToDo getToDoById(int id) {
		for(ToDo todo : ToDoList) {
			if(todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}
	public void updateToDoById(int id, String updateTask) {
		ToDo todo = getToDoById(id);
		if(todo != null) {
			todo.setTask(updateTask);
		}
	}
	public void deleteToDoById(int id) {
		ToDo todo = getToDoById(id);
		if(todo != null) {
			this.ToDoList.remove(todo);
		}
	}
	
	public Boolean isId(int id) {
		for(ToDo todo : ToDoList) {
			if(todo.getId() == id) {
				return true;
			}
		}
		return false;
	}
}
