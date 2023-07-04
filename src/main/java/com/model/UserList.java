package com.model;

import java.util.List;
import java.util.ArrayList;

public class UserList {
	private static UserList shared = null;
	private static Object lock = new Object();
	private List<User> userList = new ArrayList<>();
	private UserList() {
		User user1 = new User("Ashwin" , "123" );
		User user2 = new User("Max", "123");
		this.userList.add(user1);
		this.userList.add(user2);
	}
	public List<User> getUserList(){
		return this.userList;
	}
	
	public static UserList getInstance() {
		if (UserList.shared == null) {
			synchronized (lock) {
				if(UserList.shared == null)
					UserList.shared = new UserList();
			}
		}
		return UserList.shared;
	}
}
