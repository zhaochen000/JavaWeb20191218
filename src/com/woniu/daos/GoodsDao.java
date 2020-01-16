package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.woniu.beans.Goods;
import com.woniu.beans.PageBeans;
import com.woniu.beans.Suppliers;
import com.woniu.beans.Types;
import com.woniu.tools.ConnectionTools;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class GoodsDao {
	// 得到所有商品信息
	public ArrayList<Goods> getAllGoods() {
		Connection con = null;
		ArrayList<Goods> list = new ArrayList<Goods>();
		try {
			
			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from goods");
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				int goodsId = res.getInt("goods_id");
				String goodsCode = res.getString("goods_code");
				String goodsName = res.getString("goods_name");
				int goodsKinds = res.getInt("types_id");
				int goodsSupplier = res.getInt("suppliers_id");
				Date goodsDate = res.getDate("goods_date");
				float goodsPrice = res.getFloat("goods_price");
				int goodsCount = res.getInt("goods_count");
				String goodsLevel = res.getString("goods_level");
				String goodsSpec = res.getString("goods_spec");
				String goodsRemarks = res.getString("goods_remarks");
				Goods goods = new Goods(goodsId,goodsCode, goodsName, goodsKinds, goodsSupplier, goodsDate, goodsPrice,goodsCount,goodsLevel, goodsSpec, goodsRemarks);
				list.add(goods);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionTools.closeConnection(con);
		}

	}

	// 增加商品信息
	public void addGoods(Goods g) throws SQLException {
		Connection con = null;
		try {
			con = ConnectionTools.getConnection();
			// ？表示占位
			PreparedStatement p = con.prepareStatement(
					"insert into goods(goods_code,goods_name,types_id,suppliers_id,goods_date,goods_price,goods_count,goods_level,goods_spec,goods_remarks)values(?,?,?,?,now(),?,?,?,?,?)");
			p.setString(1, g.getGoods_code());
			p.setString(2, g.getGoods_name());
			p.setInt(3, g.getTypes_id());
			p.setInt(4, g.getSuppliers_id());
			p.setFloat(5, g.getGoods_price());
			p.setInt(6, g.getGoods_count());
			p.setString(7, g.getGoods_level());
			p.setString(8, g.getGoods_spec());
			p.setString(9, g.getGoods_remarks());
			// 向服务器发送命令
			p.executeUpdate();

		} finally {
			// 关闭资源
			ConnectionTools.closeConnection(con);

		}

	}

	// 通过某个id号，来删除商品
	public void deleteGoodsById(int goodsIDel) throws SQLException {

		Connection con = null;
		try {
			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from goods where goods_Id=?");
			// 设置占位符
			ps.setInt(1, goodsIDel);
			// 向服务器发送命令
			ps.executeUpdate();

		} finally {
			ConnectionTools.closeConnection(con);
		}

	}

	// 通过某个id来得到商品信息
	public Goods getGoodsByID(int goodsIdOwn) throws SQLException {

		Connection con = null;
		Goods g = null;
		try {

			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from goods where goods_id=?");
			ps.setInt(1, goodsIdOwn);
			// 结果集只有一行记录，可以不用循环
			ResultSet res = ps.executeQuery();
			if (res.next()) {
				int goodsId = res.getInt("goods_id");
				String goodsCode = res.getString("goods_code");
				String goodsName = res.getString("goods_name");
				int goodsKinds = res.getInt("types_id");
				int goodsSupplier = res.getInt("suppliers_id");
				Date goodsDate = res.getDate("goods_date");
				float goodsPrice = res.getFloat("goods_price");
				int goodsCount = res.getInt("goods_count");
				String goodsLevel = res.getString("goods_level");
				String goodsSpec = res.getString("goods_spec");
				String goodsRemarks = res.getString("goods_remarks");
				 g = new Goods(goodsId,goodsCode, goodsName, goodsKinds, goodsSupplier, goodsDate, goodsPrice,goodsCount,goodsLevel, goodsSpec, goodsRemarks);
			}
			return g;

		} finally {
			ConnectionTools.closeConnection(con);
		}
	}

	// 修改商品信息并保存
	public void UpdateGoods(Goods g) throws SQLException {

		Connection con = null;
		try {
			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"update goods set goods_code=?,goods_name=?,types_id=?, suppliers_id=?,goods_price=?,goods_count=?,goods_level=?,goods_spec=?,goods_remarks=? where goods_id =?");

			ps.setString(1, g.getGoods_code());
			ps.setString(2, g.getGoods_name());
			ps.setInt(3, g.getTypes_id());
			ps.setInt(4, g.getSuppliers_id());
			ps.setFloat(5, g.getGoods_price());
			ps.setInt(6,g.getGoods_count());
			ps.setString(7, g.getGoods_level());
			ps.setString(8, g.getGoods_spec());
			ps.setString(9, g.getGoods_remarks());
			ps.setInt(10, g.getGoods_id());
			// 向服务器发送命令
			ps.executeUpdate();

		} finally {
			// 关闭资源
			ConnectionTools.closeConnection(con);
		}
	}
	
	//显示分页的方法
	public List<Goods> getAllGoods(PageBeans<Goods> pab){
		Connection con = null;
		List<Goods> list = new ArrayList<Goods>();
		try {
			con = ConnectionTools.getConnection();
			//查询语句
			String sql = "select * from goods ";
			//拼接好的sql分页语句
			sql =sql + "limit " + (pab.getCurrentPage()-1)*pab.getPageSize() +"," + pab.getPageSize();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				int goodsId = res.getInt("goods_id");
				String goodsCode = res.getString("goods_code");
				String goodsName = res.getString("goods_name");
				int goodsKinds = res.getInt("types_id");
				int goodsSupplier = res.getInt("suppliers_id");
				Date goodsDate = res.getDate("goods_date");
				float goodsPrice = res.getFloat("goods_price");
				int goodsCount =res.getInt("goods_count");
				String goodsLevel = res.getString("goods_level");
				String goodsSpec = res.getString("goods_spec");
				String goodsRemarks = res.getString("goods_remarks");
				Goods goods = new Goods(goodsId,goodsCode, goodsName, goodsKinds, goodsSupplier, goodsDate, goodsPrice,goodsCount,goodsLevel, goodsSpec, goodsRemarks);

				list.add(goods);
				
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionTools.closeConnection(con);
		}

	}
    //得到总的条目数据
	public int getCount() throws SQLException {
		// TODO Auto-generated method stub
		Connection con  = ConnectionTools.getConnection();
		int count = 0;//统计数目
		try {
			//依据商品ID查询总的条目数
			PreparedStatement ps = con.prepareStatement("select count(goods_id) as 'ko' from goods");
		     //返回结果集合
			ResultSet res = ps.executeQuery();
			//结果集只有一行，不用循环，直接得到
			if(res.next()){
				count = res.getInt("ko");
			}
		
		     return count;
		
		
		}finally{
			ConnectionTools.closeConnection(con);
		}
		
	}

	//包含查询条件和分页的方法
	public List<Goods> getAllGoods(PageBeans<Goods> pab, Goods goodsQueryBean) {
		
		Connection con = null;
		List<Goods> list = new ArrayList<Goods>();
		try {
			con = ConnectionTools.getConnection();
			//拼接查询语句
			String sql = "select goods_id,goods_code,goods_name,goods.types_id,goods.suppliers_id,goods_date,goods_price,goods_count,goods_level,goods_spec,goods_remarks,types_name,suppliers_name from(goods left join types on types.types_id = goods.types_id) left join suppliers on goods.suppliers_id=suppliers.suppliers_id where 2=2 ";
			 
			//判断商品名称是否输入的情况
			//判断商品名称是否输入的情况
			if(goodsQueryBean.getGoods_name()!=null && !goodsQueryBean.getGoods_name().equals("")){
				sql = sql + " and goods_name like ?";
			}
			//判断商品规格型号是否输入的情况
			if(goodsQueryBean.getGoods_code()!=null && !goodsQueryBean.getGoods_code().equals("")){
				sql = sql + " and goods_code like ?";
			}
			
			//拼接好的sql带查询功能的分页语句
			sql =sql + " limit " + (pab.getCurrentPage()-1)*pab.getPageSize() +"," + pab.getPageSize();
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			//设置占位符的值,由于不确定占位符的个数，采用计数器
			int count=0;
			if(goodsQueryBean.getGoods_name()!=null && !goodsQueryBean.getGoods_name().equals("")){
				count++;
				ps.setString(count,"%" + goodsQueryBean.getGoods_name() + "%");
				
			}
			
			if(goodsQueryBean.getGoods_code()!=null && !goodsQueryBean.getGoods_code().equals("")){
				count++;	
				ps.setString(count,"%"+goodsQueryBean.getGoods_code()+"%");
			}
			
			//System.out.println(sql);
			//返回结果集
			ResultSet res = ps.executeQuery();
				
			while (res.next()) {
				int goodsId = res.getInt("goods_id");
				String goodsCode = res.getString("goods_code");
				String goodsName = res.getString("goods_name");
				int goodsKinds = res.getInt("types_id");
				int goodsSupplier = res.getInt("suppliers_id");
				Date goodsDate = res.getDate("goods_date");
				float goodsPrice = res.getFloat("goods_price");
				int goodsCount = res.getInt("goods_count");
				String goodsLevel = res.getString("goods_level");
				String goodsSpec = res.getString("goods_spec");
				String goodsRemarks = res.getString("goods_remarks");
				String typesName=res.getString("types_name");
				String suppliersName=res.getString("suppliers_name");
				
				Types types = new Types();
				Suppliers suppliers = new Suppliers();
				types.setTypes_name(typesName);
				suppliers.setSuppliers_name(suppliersName);
			    
				//封装对象
				Goods goods = new Goods(goodsId,goodsCode, goodsName, goodsKinds, goodsSupplier, goodsDate, goodsPrice,goodsCount,goodsLevel, goodsSpec, goodsRemarks, types, suppliers);

				list.add(goods);
				
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionTools.closeConnection(con);
		}
	}

	public int getCount(Goods goodsQueryBean) throws SQLException{
		Connection con  = ConnectionTools.getConnection();
		int count = 0;//统计数目
		try {
			
			String sql = "select count(goods_id) as 'ko' from(goods left join types on types.types_id = goods.types_id) left join suppliers on goods.suppliers_id=suppliers.suppliers_id where 2=2 ";
			//判断商品名称是否输入的情况
			if(goodsQueryBean.getGoods_name()!=null && !goodsQueryBean.getGoods_name().equals("")){
				sql= sql + " and goods_name like ?";
			}
			//判断商品规格型号是否输入的情况
			if(goodsQueryBean.getGoods_code()!=null && !goodsQueryBean.getGoods_code().equals("")){
				sql = sql + " and goods_code like ?";
			}
			
			//依据商品ID查询总的条目数
			PreparedStatement ps = con.prepareStatement(sql);
			
			//设置占位符的值,由于不确定占位符的个数，采用计数器
			if(goodsQueryBean.getGoods_name()!=null && !goodsQueryBean.getGoods_name().equals("")){
				count++;
				ps.setString(count,"%" + goodsQueryBean.getGoods_name() + "%");
				
			}
			
			if(goodsQueryBean.getGoods_code()!=null && !goodsQueryBean.getGoods_code().equals("")){
				count++;	
				ps.setString(count,"%" + goodsQueryBean.getGoods_code() + "%");
			}
			
			//返回结果集合
			ResultSet res = ps.executeQuery();
			//结果集只有一行，不用循环，直接得到
			if(res.next()){
				count = res.getInt("ko");
			}
		
		     return count;
		
		
		}finally{
			ConnectionTools.closeConnection(con);
		}
		
	}
	
}
