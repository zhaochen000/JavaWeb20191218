package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.woniu.beans.Goods;
import com.woniu.beans.OutStack;
import com.woniu.beans.PageBeans;
import com.woniu.tools.ConnectionTools;

public class OutStackDao {
    //查询所有的出库单信息
	public List<OutStack> getAllOutStack(){
		
		Connection conn = null;
		List<OutStack> list = new ArrayList<OutStack>();
		try {
			conn = ConnectionTools.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from outStack");
			//返回结果集
			ResultSet res = ps.executeQuery();
			while(res.next()){
				int outStack_id = res.getInt("outStack_id");
				int goods_id = res.getInt("goods_id");
				int user_id = res.getInt("user_id");
				String outStack_code = res.getString("outStack_code");
				int outStack_count = res.getInt("outStack_count");
				Date outStack_time = res.getDate("outStack_time");
				String outStack_status = res.getString("outStack_status");
				String outStack_remarks = res.getString("outStack_remarks");
				//封装成对象
				OutStack outStack = new OutStack(outStack_id,goods_id,user_id,outStack_code,outStack_count,outStack_time,outStack_status,outStack_remarks);
				list.add(outStack);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			ConnectionTools.closeConnection(conn);
		}
		
		return list;
		
	}

	public void addOutStack(OutStack outStack) throws SQLException {
		Connection con = null;
		try {
			con = ConnectionTools.getConnection();
			// ？表示占位
			PreparedStatement p = con.prepareStatement("insert into outStack(goods_id,user_id,outStack_code,outStack_count,outStack_time,outStack_status,outStack_remarks)values(?,?,?,?,now(),'未确认',?)");
			p.setInt(1,outStack.getGoods_id());
			p.setInt(2, outStack.getUser_id());
			p.setString(3,outStack.getOutStack_code());
			p.setInt(4, outStack.getOutStack_count());
			p.setString(5, outStack.getOutStack_remarks());
			// 向服务器发送命令
			p.executeUpdate();

		} finally {
			// 关闭资源
			ConnectionTools.closeConnection(con);

		}

		
	}

	public void deleteOutStackById(int outStackDel) throws SQLException {
		Connection con = null;
		try {
			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from outStack where outStack_id=?");
			// 设置占位符
			ps.setInt(1, outStackDel);
			// 向服务器发送命令
			ps.executeUpdate();

		} finally {
			ConnectionTools.closeConnection(con);
		}
		
	}

	public OutStack getOutStackByID(int parseInt) throws SQLException {
		Connection con = null;
		OutStack out = null;
		try {

			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from outStack where outStack_id=?");
			ps.setInt(1, parseInt);
			// 结果集只有一行记录，可以不用循环
			ResultSet res = ps.executeQuery();
			if (res.next()) {
				int outStackId = res.getInt("outStack_id");
				int goods_id = res.getInt("goods_id");
				int user_id = res.getInt("user_id");
				String outStack_code = res.getString("outStack_code");
				int outStack_count = res.getInt("outStack_count");
				Date outStack_time = res.getDate("outStack_time");
				String outStack_status = res.getString("outStack_status");
				String outStack_remarks = res.getString("outStack_remarks");
				
				out = new OutStack(outStackId,goods_id,user_id,outStack_code,outStack_count,outStack_time,outStack_status,outStack_remarks);  
			}
			
			return out;

		} finally {
			ConnectionTools.closeConnection(con);
		}
	}

	public void UpdateOutStack(OutStack outStack) throws SQLException {
		Connection con = null;
		try {
			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("update outStack set goods_id=?,user_id=?,outStack_code=?,outStack_count=?,outStack_remarks=? where outStack_id =?");
            //设置占位符的值
			ps.setInt(1, outStack.getGoods_id());
			ps.setInt(2,outStack.getUser_id());
			ps.setString(3, outStack.getOutStack_code());
			ps.setFloat(4,outStack.getOutStack_count());
			ps.setString(5,outStack.getOutStack_remarks());
			ps.setInt(6, outStack.getOutStack_id());
			
			// 向服务器发送命令
			ps.executeUpdate();

		} finally {
			// 关闭资源
			ConnectionTools.closeConnection(con);
		}
		
	}
    //出库操作
	public void confirmInStack(OutStack outStack) throws SQLException {
		
		Connection conn=null;
		try {
			conn=ConnectionTools.getConnection();
			//对出库单的状态进行修改
			PreparedStatement ps=conn.prepareStatement("update outStack set outStack_status='已确认' where outStack_id=?");
			ps.setInt(1, outStack.getOutStack_id());
			ps.executeUpdate();
			
			//出库操作
			ps=conn.prepareStatement("update goods set goods_count=goods_count-? where goods_id=?");
			ps.setInt(1, outStack.getOutStack_count());
			ps.setInt(2, outStack.getGoods_id());
			ps.executeUpdate();
			
		} 
		finally{
			ConnectionTools.closeConnection(conn);
			
		}	
		
	}
    //带查询条件的方法
	public List<OutStack> getAllOutStack(String outStackCode, String goodsName) throws SQLException {
		Connection conn=null;
		List<OutStack> outStackList=new ArrayList<OutStack>();
		try {
			conn=ConnectionTools.getConnection();
		    //拼接sql语句，多表联合查询
			String sql="select outStack_id,outStack.goods_id,user_id,outStack_code,outStack_count,outStack_time,outStack_status,outStack_remarks,goods_name from outStack left join goods on outStack.goods_id=goods.goods_id where 1=1 ";
			//拼接带查询条件的sql语句
			if(outStackCode!=null && !outStackCode.equals("")){
				sql = sql + " and outStack_code like ?";
				
			}
			
			if(goodsName!=null && !goodsName.equals("")){
				
				sql = sql + " and goods_name like ?";
			}
	
			//对sql语句进行编译
			PreparedStatement ps=conn.prepareStatement(sql);
			
			int count=0;
			//占位符的个数不确定，采用计数器
			if(outStackCode!=null && !outStackCode.equals("")){
				count++;
				ps.setString(count,"%" + outStackCode + "%" );
				
			}
			
			if(goodsName!=null && !goodsName.equals("")){
				count++;
				ps.setString(count, "%" + goodsName + "%");
			}
			
			ResultSet rs=ps.executeQuery();
		   
		    while(rs.next()){
		    	int outStack_id=rs.getInt("outStack_id");
		    	int goods_id=rs.getInt("goods_id");
		    	int user_Id=rs.getInt("user_Id");
		    	String outStack_code=rs.getString("outStack_code");
		    	int outStack_count=rs.getInt("outStack_count");
		    	Date outStack_time=rs.getDate("outStack_time");
		    	String outStack_status=rs.getString("outStack_status");
		    	String outStack_remarks=rs.getString("outStack_remarks");
		    	String goods_name=rs.getString("goods_name");
		    	//设置要查询的商品名称的值
		    	Goods g=new Goods();
		    	g.setGoods_name(goods_name);
		    	//封装成对象
		    	OutStack outStack = new OutStack(outStack_id,goods_id,user_Id,outStack_code,outStack_count,outStack_time,outStack_status,outStack_remarks,g);
		    	
		    	outStackList.add(outStack);
		    	
		    }
		    return outStackList;
			
			
		} 
		finally{
			
			ConnectionTools.closeConnection(conn);
			
		}
	}


	public List<OutStack> getAllOutStack(String outStackCode, String goodsName, PageBeans<OutStack> pab) throws SQLException {
		Connection conn=null;
		try {
			conn=ConnectionTools.getConnection();
		    //拼接sql语句，多表联合查询
			String sql="select outStack_id,outStack.goods_id,user_id,outStack_code,outStack_count,outStack_time,outStack_status,outStack_remarks,goods_name from outStack left join goods on outStack.goods_id=goods.goods_id where 1=1 ";
			//拼接带查询条件的sql语句
			if(outStackCode!=null && !outStackCode.equals("")){
				sql = sql + " and outStack_code like ?";
				
			}
			
			if(goodsName!=null && !goodsName.equals("")){
				
				sql = sql + " and goods_name like ?";
			}
			
			List<OutStack> outStackList=new ArrayList<OutStack>();
			
			sql = sql + " limit " + (pab.getCurrentPage()-1)*pab.getPageSize() +"," + pab.getPageSize();
			
			//对sql语句进行编译
			PreparedStatement ps=conn.prepareStatement(sql);
			
			int count=0;
			//占位符的个数不确定，采用计数器
			if(outStackCode!=null && !outStackCode.equals("")){
				count++;
				ps.setString(count,"%" + outStackCode + "%" );
				
			}
			
			if(goodsName!=null && !goodsName.equals("")){
				count++;
				ps.setString(count, "%" + goodsName + "%");
			}
			
			ResultSet rs=ps.executeQuery();
		   
		    while(rs.next()){
		    	int outStack_id=rs.getInt("outStack_id");
		    	int goods_id=rs.getInt("goods_id");
		    	int user_Id=rs.getInt("user_Id");
		    	String outStack_code=rs.getString("outStack_code");
		    	int outStack_count=rs.getInt("outStack_count");
		    	Date outStack_time=rs.getDate("outStack_time");
		    	String outStack_status=rs.getString("outStack_status");
		    	String outStack_remarks=rs.getString("outStack_remarks");
		    	String goods_name=rs.getString("goods_name");
		    	//设置要查询的商品名称的值
		    	Goods g=new Goods();
		    	g.setGoods_name(goods_name);
		    	//封装成对象
		    	OutStack outStack = new OutStack(outStack_id,goods_id,user_Id,outStack_code,outStack_count,outStack_time,outStack_status,outStack_remarks,g);
		    	
		    	outStackList.add(outStack);
		    	
		    }
		    return outStackList;
			
			
		} 
		finally{
			
			ConnectionTools.closeConnection(conn);
			
		}
	
	
	}

	public int getTotalCount(String outStackCode, String goodsName) throws SQLException {
		
		Connection conn=null;
		try {
			conn=ConnectionTools.getConnection();
		    //拼接sql语句，多表联合查询
			String sql="select count(outStack_id) as 'ok' from outStack left join goods on outStack.goods_id=goods.goods_id where 1=1 ";
			//拼接带查询条件的sql语句
			if(outStackCode!=null && !outStackCode.equals("")){
				sql = sql + " and outStack_code like ?";
				
			}
			
			if(goodsName!=null && !goodsName.equals("")){
				
				sql = sql + " and goods_name like ?";
			}
		
			
			//对sql语句进行编译
			PreparedStatement ps=conn.prepareStatement(sql);
			
			int count=0;
			//占位符的个数不确定，采用计数器
			if(outStackCode!=null && !outStackCode.equals("")){
				count++;
				ps.setString(count,"%" + outStackCode + "%" );
				
			}
			
			if(goodsName!=null && !goodsName.equals("")){
				count++;
				ps.setString(count, "%" + goodsName + "%");
			}
			
			ResultSet rs=ps.executeQuery();
		   
			if(rs.next()){
				
				return rs.getInt("ok");
				
			}else {
				return 0;
			}
			
			
		 
			
			
		} 
		finally{
			
			ConnectionTools.closeConnection(conn);
			
		}
	
	
	}
	
}
