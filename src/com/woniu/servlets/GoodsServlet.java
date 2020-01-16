package com.woniu.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.woniu.beans.Goods;
import com.woniu.beans.PageBeans;
import com.woniu.beans.Suppliers;
import com.woniu.beans.Types;
import com.woniu.daos.GoodsDao;
import com.woniu.daos.SuppliersDao;
import com.woniu.daos.TypesDao;
@WebServlet({"/goods/goods.do","/goods/goodsAdd.do","/goods/goodsAddUI.do","/goods/goodsDelete.do","/goods/goodsUpdate.do","/goods/goodsUpdSave.do"})
public class GoodsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);

	}

	private void getAllGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
		//*����������ѯ��Ʒ��Ϣ
		//�ӽ���õ���ѯ������
		//req.setCharacterEncoding("UTF-8");
		String goodsName = req.getParameter("goodsName");
		String goodsCode = req.getParameter("goodsCode");
		//��������װΪGoods���͵Ķ���
		Goods goodsQueryBean = new Goods();
		goodsQueryBean.setGoods_name(goodsName);
		goodsQueryBean.setGoods_code(goodsCode);
		

		//������ҳ����
		PageBeans<Goods> pab =new PageBeans<Goods>();
		
		//1.����ÿҳ��ʾ����Ŀ
		int pageSize = 2;
		//�õ�������������õ��ӽ��������ÿҳ��Ŀ��
	    String StrpageSize = req.getParameter("pageSize");
	    //ת����int����ÿҳ�ķ�ҳ��Ŀ
	    if(StrpageSize != null){
	    	pageSize = Integer.parseInt(StrpageSize);
	    }
	    //����ÿҳ��ҳ��Ŀ
	    pab.setPageSize(pageSize);
	    
	    //������Ʒ����
		GoodsDao goodsDao = new GoodsDao();
		
		//2.����Dao����룬�õ������ѯ����������Ŀ����
		try {
			int count = goodsDao.getCount(goodsQueryBean);
			//�����ܵ���Ŀ���������Ρ�
			pab.setTotalCount(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//3.����ǰҳ
		int currentPage = 1;
		String tempCurrentePage = req.getParameter("currPage");
		if(tempCurrentePage != null){
			currentPage = Integer.parseInt(tempCurrentePage);
		}
		//��ֹ�����һҳ��ʱ��Խ��
		if(currentPage < 1){
			currentPage = 1;	
		}
		//��ֹ�����һҳ��ʱ��Խ��
		if(currentPage > pab.getPages()){
			currentPage =pab.getPages();
			if(pab.getPages()==0){
				currentPage=1;
			}
		}
		//���õ�ǰҳ
		pab.setCurrentPage(currentPage);
		
		//����Dao����룬�õ�������Ʒ��Ϣ�������շ�ҳ��׼����ʾ��
		List<Goods> list = goodsDao.getAllGoods(pab,goodsQueryBean);
		
		//����ÿҳ��ʾ������
	    pab.setData(list);
		/*��pagebeans���󣨷�װ�˷�ҳ�Ͳ�ѯ��ص�������Ϣ������ÿҳ��ʾ�����ݣ����͵�jspҳ��*/
		req.setAttribute("pab", pab);
		req.setAttribute("gqb",goodsQueryBean);
		// �õ�����ת������ת������ҳ��
		req.getRequestDispatcher("goods.jsp").forward(req, resp);;
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �õ�����·��
		String path = req.getServletPath();
		SuppliersDao suppliersDao =new SuppliersDao();
		TypesDao typesDao =new TypesDao();
		// չʾ������Ϣ
		if (path.equals("/goods/goods.do")) {

			getAllGoods(req, resp);
			
		}else if (path.equals("/goods/goodsAddUI.do")) {
			//��Ʒ��Ϣ
			List<Suppliers> sList= null;
			try {
				sList = suppliersDao.getAllSuppliers();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("suppliersList", sList);
			List<Types> tList = typesDao.getAllTypes();
			req.setAttribute("typesList", tList);
			req.getRequestDispatcher("goodsAdd.jsp").forward(req, resp);


		} else if (path.equals("/goods/goodsAdd.do")) {
			// �������������Ϣ������
			//req.setCharacterEncoding("UTF-8");

			// ���������ڽ��������
            String goodsCode = req.getParameter("goodsCode");
			String goodsName = req.getParameter("goodsName");
			String goodsKinds = req.getParameter("goodsKinds");
			String goodsSupplier = req.getParameter("goodsSupplier");
			String goodsPrice = req.getParameter("goodsPrice");
			String goodsCount = req.getParameter("goodsCount");
			String goodsLevel = req.getParameter("goodsLevel");
			String goodsSpec = req.getParameter("goodsSpec");
			String goodsRemarks = req.getParameter("goodsRemarks");

			Goods g = new Goods(goodsCode,goodsName, Integer.parseInt(goodsKinds), Integer.parseInt(goodsSupplier),
					Float.parseFloat(goodsPrice), Integer.parseInt(goodsCount),goodsLevel, goodsSpec, goodsRemarks);

			// ����dao�����
			GoodsDao goodsDao = new GoodsDao();
			try {
				goodsDao.addGoods(g);
				// ���ӳɹ���չʾ���е���Ʒ��Ϣ
				resp.sendRedirect("goods.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (path.equals("/goods/goodsDelete.do")) {
			// �õ�Ҫɾ������Ʒid
			String goodsId = req.getParameter("goodsId");
			// ����dao��������ɾ��
			GoodsDao goodsDao = new GoodsDao();
			try {
				goodsDao.deleteGoodsById(Integer.parseInt(goodsId));
				resp.sendRedirect("goods.do");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (path.equals("/goods/goodsUpdate.do")) {
			String goodsId = req.getParameter("goodsId");
			GoodsDao goodsDao = new GoodsDao();
			List<Suppliers> sList =null;
			Goods g = null;
			try {
				g = goodsDao.getGoodsByID(Integer.parseInt(goodsId));
                sList = suppliersDao.getAllSuppliers();
               
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			List<Types> tList = typesDao.getAllTypes();
			req.setAttribute("typesList", tList);
			req.setAttribute("suppliersList", sList);
			// ���õ���id��װ��goods��
			req.setAttribute("goods", g);
			// ����ת����
			req.getRequestDispatcher("goodsUpdate.jsp").forward(req, resp);;

		} else if (path.equals("/goods/goodsUpdSave.do")) {
			// �������������Ϣ������(��Ա���Post����)
			//req.setCharacterEncoding("UTF-8");
			// �õ��������,��������
			int goodsId = Integer.parseInt(req.getParameter("goodsId"));
			String goodsCode = req.getParameter("goodsCode");
			String goodsName = req.getParameter("goodsName");
			int goodsKinds = Integer.parseInt(req.getParameter("goodsKinds"));
			int goodsSupplier = Integer.parseInt(req.getParameter("goodsSupplier"));
			float goodsPrice = Float.parseFloat(req.getParameter("goodsPrice"));
			int goodsCount = Integer.parseInt(req.getParameter("goodsCount"));
			String goodsLevel = req.getParameter("goodsLevel");
			String goodsSpec = req.getParameter("goodsSpec");
			String goodsRemarks = req.getParameter("goodsRemarks");
			// ��װ����
			Goods g = new Goods(goodsId,goodsCode,goodsName, goodsKinds, goodsSupplier,goodsPrice, goodsCount,goodsLevel, goodsSpec, goodsRemarks);

			// ����Dao�㷽��
			GoodsDao goodsDao = new GoodsDao();
			try {
				goodsDao.UpdateGoods(g);
				resp.sendRedirect("goods.do");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
