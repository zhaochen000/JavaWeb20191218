package com.woniu.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniu.beans.Goods;
import com.woniu.beans.OutStack;
import com.woniu.beans.PageBeans;
import com.woniu.daos.GoodsDao;
import com.woniu.daos.OutStackDao;
/**
 * Servlet implementation class OutStackServlet
 */
@WebServlet({ "/outStack/outStack.do", "/outStack/outStackAdd.do","/outStack/outStackAddUI.do","/outStack/outStackDel.do",
		"/outStack/outStackUpd.do", "/outStack/outStackUpdSave.do","/outStack/inStackConfirm.do"})
public class OutStackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OutStackServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �õ�����·��
		request.setCharacterEncoding("UTF-8");
		String path = request.getServletPath();
		OutStackDao outStackDao = new OutStackDao();
		if (path.equals("/outStack/outStack.do")) {
			// ����Dao������ѯ���ⵥ��Ϣ
			
			//���մӽ��洫�����Ĳ�ѯ����
			String outStackCode= request.getParameter("outStackCode");
			String goodsName= request.getParameter("goodsName");
			try {
			//������ҳ����
			PageBeans<OutStack> pab = new PageBeans<OutStack>();
		    int pageSize = 3;
		    //���մӽ��洫������ÿҳ��ʾ�������Ŀ�� 
		    String tempPageSize = request.getParameter("pageSize");
		    //ҳ����μ���ʱ�ж���ʾ����Ŀ���Ƿ�Ϊnull
		    if(tempPageSize != null){
		    	
		    	pageSize = Integer.parseInt(tempPageSize);
		    	
		    }
			
		    //����ÿҳ��ʾ�ĵ���Ŀ��
		    pab.setPageSize(pageSize);
			
			//�����ܵ���Ŀ��
			int totalCount = outStackDao.getTotalCount(outStackCode, goodsName);
			pab.setTotalCount(totalCount);
		
		    
			//����ǰҳ
			int currentPage = 1;
			String tempCurrentePage = request.getParameter("cutPage");
			if(tempCurrentePage != null){
				
				currentPage = Integer.parseInt(tempCurrentePage);
			}
			//��ֹ�����һҳ��ʱ��Խ��
			if(currentPage < 1){
				currentPage = 1;	
			}
			//��ֹ�����һҳ��ʱ��Խ��
			if(currentPage > pab.getPages()){
				currentPage = pab.getPages();
				if(pab.getPages()==0){
            		
            		currentPage=1;
            	}
				
			}
			//���õ�ǰҳ����
			pab.setCurrentPage(currentPage);
			
			//���ô���ѯ�����ͷ�ҳ�ķ���
			List<OutStack> list = outStackDao.getAllOutStack(outStackCode,goodsName,pab);
				
			//����Map������Ӵӽ��洫����������
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sCode", outStackCode);
			map.put("gName", goodsName);
			
			//��������
			pab.setData(list);
			request.setAttribute("pab", pab);
			request.setAttribute("queryMap", map);

			// ����ת����jspҳ��
			request.getRequestDispatcher("outStack.jsp").forward(request, response);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (path.equals("/outStack/outStackAddUI.do")) {
			//��Ʒ��Ϣ
			GoodsDao goodsDao = new GoodsDao();
			List<Goods> li= goodsDao.getAllGoods();
			
			request.setAttribute("goodsList", li);
			request.getRequestDispatcher("outStackAdd.jsp").forward(request, response);

		} else if (path.equals("/outStack/outStackAdd.do")) {

			//request.setCharacterEncoding("UTF-8");
			String outStackCode = request.getParameter("outStackCode");
			String goodsId = request.getParameter("goodsId");
			int userId = Integer.parseInt((String)(request.getSession().getAttribute("uid")));
			String outStackCount = request.getParameter("outStackCount");
			String outStackRemarks = request.getParameter("outStackRemarks");
			// ��װ��Suppliers���͵Ķ���
			OutStack outStack = new OutStack(Integer.parseInt(goodsId),userId, outStackCode, Integer.parseInt(outStackCount),outStackRemarks);
			// ����Dao�����
			try {
		
				outStackDao.addOutStack(outStack);
				// ���ӳɹ���ʾ������Ʒ��Ϣ
				response.sendRedirect("outStack.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		} else if (path.equals("/outStack/outStackDel.do")) {

			// �õ�Ҫɾ������Ʒid
			String outStackId = request.getParameter("outStackId");
			// ����dao��������ɾ��
			try {
				outStackDao.deleteOutStackById(Integer.parseInt(outStackId));
				response.sendRedirect("outStack.do");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (path.equals("/outStack/outStackUpd.do")) {

			String outStackId = request.getParameter("outStackId");
			OutStack out = null;
			GoodsDao goodsDao = new GoodsDao();
			try {
				out = outStackDao.getOutStackByID(Integer.parseInt(outStackId));
				//�����е���Ʒ��Ϣ�ͳ��ⵥ��Ϣ������jspҳ��
				List<Goods> goodslist = goodsDao.getAllGoods();
				request.setAttribute("goodsList", goodslist);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// ���õ���id��װ��goods��
			request.setAttribute("outStack", out);
			// ����ת����
			request.getRequestDispatcher("outStackUpd.jsp").forward(request, response);
		

		} else if (path.equals("/outStack/outStackUpdSave.do")) {

			// �������������Ϣ������(��Ա���Post����)
			//request.setCharacterEncoding("UTF-8");
			// �õ��������,��������
			String outStackId = request.getParameter("outStackId");
			String outStackCode = request.getParameter("outStackCode");
			String goodsId = request.getParameter("goodsId");
			int userId = 1;
			String outStackCount = request.getParameter("outStackCount");
			String outStackRemarks = request.getParameter("outStackRemarks");
			// ��װ����
			OutStack outStack = new OutStack(Integer.parseInt(outStackId),Integer.parseInt(goodsId),userId, outStackCode, Integer.parseInt(outStackCount),outStackRemarks);
			// ����Dao�㷽��
			try {
				outStackDao.UpdateOutStack(outStack);
				response.sendRedirect("outStack.do");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}else if (path.equals("/outStack/inStackConfirm.do")) {
			  //���մӽ��洫������ֵ
			  String outStackId=request.getParameter("outStackId");
			  String goodsId=request.getParameter("goodsId");
			  String outStackCount=request.getParameter("outStackCount");
			  
			  OutStack outStack = new OutStack(Integer.parseInt(outStackId),Integer.parseInt(goodsId),Integer.parseInt(outStackCount));
			  
			  try {
				outStackDao.confirmInStack(outStack);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  response.sendRedirect("outStack.do");

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
