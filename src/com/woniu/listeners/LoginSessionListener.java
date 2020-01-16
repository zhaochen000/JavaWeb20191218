package com.woniu.listeners;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.woniu.beans.LoginCache;

@WebListener
public class LoginSessionListener implements HttpSessionAttributeListener {

    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	//获得用户名
    	String key = arg0.getName();
    	//根据键得到用户名的值
		String value = (String)arg0.getValue();
		//此时在进行登录操作
		if(key.equals("uname")){
			 //获得登录缓存对象
			 LoginCache cache=LoginCache.getInstance();
			 //根据用户名找对应的session对象
			 HttpSession oldSession=cache.getSessionByUserName(value);
			 
			 if(oldSession!=null){
				 //销毁session
				 oldSession.invalidate();
				 
			 }

			 cache.saveUserSession(value, arg0.getSession());
		}
    }

	
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    	//得到application对象
    	ServletContext context = arg0.getSession().getServletContext();
    
    	if (context.getAttribute("online") != null) {

			int count = (Integer) context.getAttribute("online");
			count--;
			// 重新刷新后的在线人数
			context.setAttribute("online", count);

		}
    }


    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
