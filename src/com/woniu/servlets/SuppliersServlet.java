package com.woniu.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.woniu.beans.Suppliers;
import com.woniu.daos.SuppliersDao;
@WebServlet({"/suppliers/suppliers.do","/suppliers/suppliersAdd.do","/suppliers/suppliersDel.do","/suppliers/suppliersUpd.do","/suppliers/suppliersUpdSave.do"})
public class SuppliersServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getServletPath();// ��ȡ����·��
		SuppliersDao suppliersDao = new SuppliersDao();
		// չʾ��Ӧ����Ϣ
		if (path.equals("/suppliers/suppliers.do")) {

			List<Suppliers> li = null;
			try {
				li = suppliersDao.getAllSuppliers();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("suppList", li);
			req.getRequestDispatcher("suppliers.jsp").forward(req, resp);
			// ���ӹ�Ӧ����Ϣ
		} else if (path.equals("/suppliers/suppliersAdd.do")) {
			// �ӽ���õ��������
			//req.setCharacterEncoding("UTF-8");
			String suppliersCode = req.getParameter("suppliersCode");
			String suppliersName = req.getParameter("suppliersName");
			String suppliersPhone = req.getParameter("suppliersPhone");
			Suppliers supp = new Suppliers(0, suppliersCode, suppliersName, suppliersPhone);
			// ����Dao�����ȥ������Ϣ
			try {
				suppliersDao.addSuppliers(supp);
				resp.sendRedirect("suppliers.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// ɾ����Ӧ����Ϣ
		else if (path.equals("/suppliers/suppliersDel.do")) {
			String suppliersId = req.getParameter("suppliersId");
			try {
				
				suppliersDao.deleteSuppliersById(Integer.parseInt(suppliersId));
				resp.sendRedirect("suppliers.do");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// �޸���Ϣ
		} else if (path.equals("/suppliers/suppliersUpd.do")) {
			// �õ�Ҫ�޸ĵ���Ŀid
			String suppliersId = req.getParameter("suppliersId");
			Suppliers supp = null;
			try {
				 supp = suppliersDao.getSuppliersById(Integer.parseInt(suppliersId));
				
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("suppliers", supp);
			
			req.getRequestDispatcher("suppliersUpd.jsp").forward(req, resp);

			// �޸ı���
		} else if (path.equals("/suppliers/suppliersUpdSave.do")) {
			
			//req.setCharacterEncoding("UTF-8");
			String suppliersId = req.getParameter("suppliersId");
			String suppliersCode = req.getParameter("suppliersCode");
			String suppliersName = req.getParameter("suppliersName");
			String suppliersPhone = req.getParameter("suppliersPhone");
			Suppliers supp = new Suppliers(Integer.parseInt(suppliersId), suppliersCode, suppliersName, suppliersPhone);
			try {

				suppliersDao.UpdateSuppliers(supp);
				resp.sendRedirect("suppliers.do");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
