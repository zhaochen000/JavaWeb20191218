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
       
		//*根据条件查询商品信息
		//从界面得到查询的条件
		//req.setCharacterEncoding("UTF-8");
		String goodsName = req.getParameter("goodsName");
		String goodsCode = req.getParameter("goodsCode");
		//将参数封装为Goods类型的对象
		Goods goodsQueryBean = new Goods();
		goodsQueryBean.setGoods_name(goodsName);
		goodsQueryBean.setGoods_code(goodsCode);
		

		//创建分页对象
		PageBeans<Goods> pab =new PageBeans<Goods>();
		
		//1.设置每页显示的数目
		int pageSize = 2;
		//得到请求参数，即得到从界面输入的每页条目数
	    String StrpageSize = req.getParameter("pageSize");
	    //转化成int类型每页的分页数目
	    if(StrpageSize != null){
	    	pageSize = Integer.parseInt(StrpageSize);
	    }
	    //设置每页分页数目
	    pab.setPageSize(pageSize);
	    
	    //创建商品对象
		GoodsDao goodsDao = new GoodsDao();
		
		//2.调用Dao层代码，得到满足查询条件的总条目数量
		try {
			int count = goodsDao.getCount(goodsQueryBean);
			//设置总的条目数量，传参。
			pab.setTotalCount(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//3.处理当前页
		int currentPage = 1;
		String tempCurrentePage = req.getParameter("currPage");
		if(tempCurrentePage != null){
			currentPage = Integer.parseInt(tempCurrentePage);
		}
		//防止点击上一页的时候越界
		if(currentPage < 1){
			currentPage = 1;	
		}
		//防止点击下一页的时候越界
		if(currentPage > pab.getPages()){
			currentPage =pab.getPages();
			if(pab.getPages()==0){
				currentPage=1;
			}
		}
		//设置当前页
		pab.setCurrentPage(currentPage);
		
		//调用Dao层代码，得到所有商品信息，并按照分页标准来显示。
		List<Goods> list = goodsDao.getAllGoods(pab,goodsQueryBean);
		
		//设置每页显示的数据
	    pab.setData(list);
		/*将pagebeans对象（封装了分页和查询相关的所有信息，包括每页显示的数据）发送到jsp页面*/
		req.setAttribute("pab", pab);
		req.setAttribute("gqb",goodsQueryBean);
		// 得到请求转发器，转发到该页面
		req.getRequestDispatcher("goods.jsp").forward(req, resp);;
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 得到请求路径
		String path = req.getServletPath();
		SuppliersDao suppliersDao =new SuppliersDao();
		TypesDao typesDao =new TypesDao();
		// 展示所有信息
		if (path.equals("/goods/goods.do")) {

			getAllGoods(req, resp);
			
		}else if (path.equals("/goods/goodsAddUI.do")) {
			//商品信息
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
			// 避免请求参数信息的乱码
			//req.setCharacterEncoding("UTF-8");

			// 接收来自于界面的数据
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

			// 调用dao层代码
			GoodsDao goodsDao = new GoodsDao();
			try {
				goodsDao.addGoods(g);
				// 增加成功，展示所有的商品信息
				resp.sendRedirect("goods.do");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (path.equals("/goods/goodsDelete.do")) {
			// 得到要删除的商品id
			String goodsId = req.getParameter("goodsId");
			// 调用dao层代码完成删除
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
			// 将得到的id封装到goods中
			req.setAttribute("goods", g);
			// 请求转发器
			req.getRequestDispatcher("goodsUpdate.jsp").forward(req, resp);;

		} else if (path.equals("/goods/goodsUpdSave.do")) {
			// 避免请求参数信息的乱码(针对表单的Post请求)
			//req.setCharacterEncoding("UTF-8");
			// 得到请求参数,界面数据
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
			// 封装对象
			Goods g = new Goods(goodsId,goodsCode,goodsName, goodsKinds, goodsSupplier,goodsPrice, goodsCount,goodsLevel, goodsSpec, goodsRemarks);

			// 调用Dao层方法
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
