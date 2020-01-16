package com.woniu.beans;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class LoginCache {

	private  static LoginCache instance=new LoginCache();

	private Map<String,HttpSession> userSessions=new HashMap<String,HttpSession>();
	private LoginCache() {
		// TODO Auto-generated constructor stub
	}
	
	public static LoginCache getInstance() {
		return instance;
	}
	
	public void saveUserSession(String userName,HttpSession session){
		
		userSessions.put(userName, session);
		
	}

	public HttpSession  getSessionByUserName(String userName){
		
		return userSessions.get(userName);
		
		
	}
}
