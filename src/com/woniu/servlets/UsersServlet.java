package com.woniu.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.woniu.beans.Users;
import com.woniu.daos.UsersDao;

public class UsersServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);// ����doPost�������������ַ���
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��ȡ������·��
		String path = req.getServletPath();
		if (path.equals("/login.do")) {
			// �������
			String userName = req.getParameter("userName");
			String passWord = req.getParameter("passWord");
			// ��װ��User����
			Users ur = new Users(userName, passWord);
			UsersDao userDao = new UsersDao();
			
			try {
				int userId = userDao.isExit(ur);
				if (userId != 0) {
					// ��¼�ɹ����û����Ž�ȥ
					// �õ����ض��Ự�����Ķ���
					HttpSession ses = req.getSession();
					ses.setAttribute("uname", userName);
					ses.setAttribute("uid", String.valueOf(userId));

					// �ŵ�Cookie��ȥ
					Cookie cookie = new Cookie("uname", userName);
					cookie.setMaxAge(3600);// ����cookie��ʧЧʱ��

					// ��cookie�е���Ϣ��Ӧ���ͻ���
					String str = req.getHeader("Cookie");
					// ����cookie
					resp.addCookie(cookie);

					// ͳ����������
					int count = 0;
					ServletContext context = req.getServletContext();
					// �ж�application����û�зŹ���������
					if (context.getAttribute("online") != null) {

						count = (Integer)context.getAttribute("online");

					}

					count++;// �ۼ�
					context.setAttribute("online", count);

					// �ͻ������������content.jsp
					resp.sendRedirect("index.jsp");

					
				} else {
					resp.sendRedirect("login.jsp");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (path.equals("/exit.do")) {
			HttpSession session = req.getSession();
			// �Ƴ��û�,��ȫ�˳�����
			session.removeAttribute("uname");

			ServletContext context = req.getServletContext();
			//req.getSession().getServletContext();
			if (context.getAttribute("online") != null) {

				int count = (Integer) context.getAttribute("online");
				count--;
				// ����ˢ�º����������
				context.setAttribute("online", count);

			}

			// ������¼����
			resp.sendRedirect("login.jsp");
		}
	}

}
