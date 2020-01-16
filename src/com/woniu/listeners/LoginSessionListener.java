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
    	//����û���
    	String key = arg0.getName();
    	//���ݼ��õ��û�����ֵ
		String value = (String)arg0.getValue();
		//��ʱ�ڽ��е�¼����
		if(key.equals("uname")){
			 //��õ�¼�������
			 LoginCache cache=LoginCache.getInstance();
			 //�����û����Ҷ�Ӧ��session����
			 HttpSession oldSession=cache.getSessionByUserName(value);
			 
			 if(oldSession!=null){
				 //����session
				 oldSession.invalidate();
				 
			 }

			 cache.saveUserSession(value, arg0.getSession());
		}
    }

	
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    	//�õ�application����
    	ServletContext context = arg0.getSession().getServletContext();
    
    	if (context.getAttribute("online") != null) {

			int count = (Integer) context.getAttribute("online");
			count--;
			// ����ˢ�º����������
			context.setAttribute("online", count);

		}
    }


    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
