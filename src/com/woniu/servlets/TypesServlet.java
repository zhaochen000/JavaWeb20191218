package com.woniu.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.woniu.beans.Types;
import com.woniu.daos.TypesDao;

@WebServlet({ "/types/types.do", "/types/typesAdd.do", "/types/typesDel.do", "/types/typesUpd.do", "/types/typesUpdSave.do" })
public class TypesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 得到请求路径
		String path = req.getServletPath();

		// 展示所有信息
		if (path.equals("/types/types.do")) {
			req.setCharacterEncoding("UTF-8");
			TypesDao typesDao = new TypesDao();
			List<Types> list = typesDao.getAllTypes();

			req.setAttribute("typesList", list);
			// 得到请求转发器，转发到该页面
			req.getRequestDispatcher("types.jsp").forward(req, resp);;
			
		} else if (path.equals("/types/typesAdd.do")) {
			//req.setCharacterEncoding("UTF-8");
			String typesCode = req.getParameter("typesCode");
			String typesName = req.getParameter("typesName");
			// 封装成Suppliers类型的对象
			Types types = new Types(0, typesCode, typesName);

			// 调用Dao层代码
			TypesDao typesDao = new TypesDao();

			try {
				typesDao.addTypes(types);
				// 增加成功显示所有商品信息
				resp.sendRedirect("types.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (path.equals("/types/typesDel.do")) {

			// 得到要删除的商品id
			String typesId = req.getParameter("typesId");
			// 调用dao层代码完成删除
			TypesDao typesDao = new TypesDao();
			try {
				typesDao.deleteTypesById(Integer.parseInt(typesId));
				resp.sendRedirect("types.do");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			    
			}

		} else if (path.equals("/types/typesUpd.do")) {

			String typesId = req.getParameter("typesId");
			TypesDao typesDao = new TypesDao();
			Types t = null;
			try {
				t = typesDao.getTypesByID(Integer.parseInt(typesId));

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 将得到的id封装到goods中
			req.setAttribute("types", t);
			// 请求转发器
			req.getRequestDispatcher("typesUpd.jsp").forward(req, resp);

		} else if (path.equals("/types/typesUpdSave.do")) {

			//req.setCharacterEncoding("UTF-8");// 避免请求参数信息的乱码(针对表单的Post请求)
			// 得到请求参数,界面数据
			int typesId = Integer.parseInt(req.getParameter("typesId"));
			String typesCode = req.getParameter("typesCode");
			String typesName = req.getParameter("typesName");
	
			// 封装对象
			Types t = new Types(typesId,typesCode,typesName);
			// 调用Dao层方法
			TypesDao typesDao = new TypesDao();
			try {
				typesDao.UpdateTypes(t);
				resp.sendRedirect("types.do");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
