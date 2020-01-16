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

		// �õ�����·��
		String path = req.getServletPath();

		// չʾ������Ϣ
		if (path.equals("/types/types.do")) {
			req.setCharacterEncoding("UTF-8");
			TypesDao typesDao = new TypesDao();
			List<Types> list = typesDao.getAllTypes();

			req.setAttribute("typesList", list);
			// �õ�����ת������ת������ҳ��
			req.getRequestDispatcher("types.jsp").forward(req, resp);;
			
		} else if (path.equals("/types/typesAdd.do")) {
			//req.setCharacterEncoding("UTF-8");
			String typesCode = req.getParameter("typesCode");
			String typesName = req.getParameter("typesName");
			// ��װ��Suppliers���͵Ķ���
			Types types = new Types(0, typesCode, typesName);

			// ����Dao�����
			TypesDao typesDao = new TypesDao();

			try {
				typesDao.addTypes(types);
				// ���ӳɹ���ʾ������Ʒ��Ϣ
				resp.sendRedirect("types.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (path.equals("/types/typesDel.do")) {

			// �õ�Ҫɾ������Ʒid
			String typesId = req.getParameter("typesId");
			// ����dao��������ɾ��
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
			// ���õ���id��װ��goods��
			req.setAttribute("types", t);
			// ����ת����
			req.getRequestDispatcher("typesUpd.jsp").forward(req, resp);

		} else if (path.equals("/types/typesUpdSave.do")) {

			//req.setCharacterEncoding("UTF-8");// �������������Ϣ������(��Ա���Post����)
			// �õ��������,��������
			int typesId = Integer.parseInt(req.getParameter("typesId"));
			String typesCode = req.getParameter("typesCode");
			String typesName = req.getParameter("typesName");
	
			// ��װ����
			Types t = new Types(typesId,typesCode,typesName);
			// ����Dao�㷽��
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
