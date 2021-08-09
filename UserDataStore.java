package com.store;

import java.util.HashMap;
import java.util.Map;

public class UserDataStore {public static Map<Integer, User> userData() {
	Map<Integer,User> users = new HashMap<Integer,User>();
	users.put(1, new User("admin","admin","admin"));
	users.put(2, new User("manager","manager","manager"));
	users.put(3, new User("user","user","user"));
	return users;
	
}
}
