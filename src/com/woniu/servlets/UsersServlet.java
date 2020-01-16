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

		doPost(req, resp);// 调用doPost方法，覆盖两种方法
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取其请求路径
		String path = req.getServletPath();
		if (path.equals("/login.do")) {
			// 请求参数
			String userName = req.getParameter("userName");
			String passWord = req.getParameter("passWord");
			// 封装成User对象
			Users ur = new Users(userName, passWord);
			UsersDao userDao = new UsersDao();
			
			try {
				int userId = userDao.isExit(ur);
				if (userId != 0) {
					// 登录成功将用户名放进去
					// 得到跟特定会话关联的对象
					HttpSession ses = req.getSession();
					ses.setAttribute("uname", userName);
					ses.setAttribute("uid", String.valueOf(userId));

					// 放到Cookie中去
					Cookie cookie = new Cookie("uname", userName);
					cookie.setMaxAge(3600);// 设置cookie的失效时间

					// 将cookie中的信息响应给客户端
					String str = req.getHeader("Cookie");
					// 增加cookie
					resp.addCookie(cookie);

					// 统计在线人数
					int count = 0;
					ServletContext context = req.getServletContext();
					// 判断application中有没有放过在线人数
					if (context.getAttribute("online") != null) {

						count = (Integer)context.getAttribute("online");

					}

					count++;// 累加
					context.setAttribute("online", count);

					// 客户端浏览器请求content.jsp
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
			// 移除用户,安全退出操作
			session.removeAttribute("uname");

			ServletContext context = req.getServletContext();
			//req.getSession().getServletContext();
			if (context.getAttribute("online") != null) {

				int count = (Integer) context.getAttribute("online");
				count--;
				// 重新刷新后的在线人数
				context.setAttribute("online", count);

			}

			// 跳到登录界面
			resp.sendRedirect("login.jsp");
		}
	}

}
