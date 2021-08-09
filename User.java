package com.store;

import java.util.Map;

public class User {

	protected String usertype;
	protected String username;
	protected String password;
	
	public User() {
		
	}
	
	public User(String username,String password,String usertype) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		
	}
	
	protected static boolean checkIfRoleExists(String role) {
		
	
		Map<Integer, User> users = UserDataStore.userData();
		for(Map.Entry<Integer, User> user: users.entrySet()) {
			if(user.getValue().usertype.equals(role)) {
				return true;
			}
		}
		return false;
	}
	
	protected static boolean verifyLogin(String username,String password) {
		Map<Integer, User> users = UserDataStore.userData();
		for(Map.Entry<Integer, User> user: users.entrySet()) {
			if(user.getValue().username.equals(username) && user.getValue().password.equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		
		
	}
}



