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
		// 得到请求路径
		request.setCharacterEncoding("UTF-8");
		String path = request.getServletPath();
		OutStackDao outStackDao = new OutStackDao();
		if (path.equals("/outStack/outStack.do")) {
			// 调用Dao层代码查询出库单信息
			
			//接收从界面传过来的查询参数
			String outStackCode= request.getParameter("outStackCode");
			String goodsName= request.getParameter("goodsName");
			try {
			//创建分页对象
			PageBeans<OutStack> pab = new PageBeans<OutStack>();
		    int pageSize = 3;
		    //接收从界面传过来的每页显示的最大条目数 
		    String tempPageSize = request.getParameter("pageSize");
		    //页面初次加载时判断显示的条目数是否为null
		    if(tempPageSize != null){
		    	
		    	pageSize = Integer.parseInt(tempPageSize);
		    	
		    }
			
		    //设置每页显示的的条目数
		    pab.setPageSize(pageSize);
			
			//设置总的条目数
			int totalCount = outStackDao.getTotalCount(outStackCode, goodsName);
			pab.setTotalCount(totalCount);
		
		    
			//处理当前页
			int currentPage = 1;
			String tempCurrentePage = request.getParameter("cutPage");
			if(tempCurrentePage != null){
				
				currentPage = Integer.parseInt(tempCurrentePage);
			}
			//防止点击上一页的时候越界
			if(currentPage < 1){
				currentPage = 1;	
			}
			//防止点击下一页的时候越界
			if(currentPage > pab.getPages()){
				currentPage = pab.getPages();
				if(pab.getPages()==0){
            		
            		currentPage=1;
            	}
				
			}
			//设置当前页数据
			pab.setCurrentPage(currentPage);
			
			//调用带查询条件和分页的方法
			List<OutStack> list = outStackDao.getAllOutStack(outStackCode,goodsName,pab);
				
			//利用Map集合添加从界面传过来的数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sCode", outStackCode);
			map.put("gName", goodsName);
			
			//设置数据
			pab.setData(list);
			request.setAttribute("pab", pab);
			request.setAttribute("queryMap", map);

			// 请求转发到jsp页面
			request.getRequestDispatcher("outStack.jsp").forward(request, response);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (path.equals("/outStack/outStackAddUI.do")) {
			//商品信息
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
			// 封装成Suppliers类型的对象
			OutStack outStack = new OutStack(Integer.parseInt(goodsId),userId, outStackCode, Integer.parseInt(outStackCount),outStackRemarks);
			// 调用Dao层代码
			try {
		
				outStackDao.addOutStack(outStack);
				// 增加成功显示所有商品信息
				response.sendRedirect("outStack.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		} else if (path.equals("/outStack/outStackDel.do")) {

			// 得到要删除的商品id
			String outStackId = request.getParameter("outStackId");
			// 调用dao层代码完成删除
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
				//将所有的商品信息和出库单信息发送至jsp页面
				List<Goods> goodslist = goodsDao.getAllGoods();
				request.setAttribute("goodsList", goodslist);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 将得到的id封装到goods中
			request.setAttribute("outStack", out);
			// 请求转发器
			request.getRequestDispatcher("outStackUpd.jsp").forward(request, response);
		

		} else if (path.equals("/outStack/outStackUpdSave.do")) {

			// 避免请求参数信息的乱码(针对表单的Post请求)
			//request.setCharacterEncoding("UTF-8");
			// 得到请求参数,界面数据
			String outStackId = request.getParameter("outStackId");
			String outStackCode = request.getParameter("outStackCode");
			String goodsId = request.getParameter("goodsId");
			int userId = 1;
			String outStackCount = request.getParameter("outStackCount");
			String outStackRemarks = request.getParameter("outStackRemarks");
			// 封装对象
			OutStack outStack = new OutStack(Integer.parseInt(outStackId),Integer.parseInt(goodsId),userId, outStackCode, Integer.parseInt(outStackCount),outStackRemarks);
			// 调用Dao层方法
			try {
				outStackDao.UpdateOutStack(outStack);
				response.sendRedirect("outStack.do");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}else if (path.equals("/outStack/inStackConfirm.do")) {
			  //接收从界面传过来的值
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
