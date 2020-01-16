package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.woniu.beans.Suppliers;
import com.woniu.tools.ConnectionTools;

public class SuppliersDao {

	public List<Suppliers> getAllSuppliers() throws SQLException{
		List<Suppliers> li = new ArrayList<Suppliers>();
		Connection con=null;;
		try {
			con= ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from suppliers");
			//返回结果集
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				int suppliersID = res.getInt("suppliers_id");
				String suppliersCode = res.getString("suppliers_code");
				String suppliersName = res.getString("suppliers_name");
				String suppliersPhone = res.getString("suppliers_phone");
				//封装成Suppliers类型的对象
				Suppliers sup = new Suppliers(suppliersID,suppliersCode,suppliersName,suppliersPhone);
				//添加到集合中去
				li.add(sup);
				
			}
			//System.out.println(li);
			return  li;
		}finally{
			ConnectionTools.closeConnection(con);
		}

		
	}
	
	public void addSuppliers(Suppliers sup) throws SQLException {
		
		Connection con =null; 
		try {
			//连接数据库
			con = ConnectionTools.getConnection();
			//得到对象
			PreparedStatement ps = con.prepareStatement("insert into suppliers(suppliers_code,suppliers_name,suppliers_phone) value(?,?,?)");
		    //设置占位符和值
			ps.setString(1, sup.getSuppliers_code());
			ps.setString(2, sup.getSuppliers_name());
            ps.setString(3, sup.getSuppliers_phone());	
            //向服务器发送命令
            ps.executeUpdate();
		
		}finally{
			ConnectionTools.closeConnection(con);
		}
	}
	
	public void deleteSuppliersById(int suppDel) throws SQLException{
		
		Connection con =null;
		try {
			con = ConnectionTools.getConnection();
			PreparedStatement ps= con.prepareStatement("delete from suppliers where suppliers_id=?");
		    //设置占位符
			ps.setInt(1, suppDel);
	
			//向服务器发送命令
			ps.executeUpdate();
		}finally{
			ConnectionTools.closeConnection(con);
		}
		
	}
	
	public Suppliers getSuppliersById(int suppliersIds) throws SQLException{
		

		Connection con = null;
		Suppliers sup = null;
		try {

			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from suppliers where suppliers_id=?");
			ps.setInt(1, suppliersIds);
			// 结果集只有一行记录，可以不用循环
			ResultSet res = ps.executeQuery();
			if (res.next()) {
				int suppliersId = res.getInt("suppliers_id");
				String suppliersCode = res.getString("suppliers_code");
				String suppliersName = res.getString("suppliers_name");
				String suppliersPhone = res.getString("suppliers_phone");
			    //封装成对象
			    sup = new Suppliers(suppliersId,suppliersCode,suppliersName,suppliersPhone);	
				
			}
			return sup;

		} finally {
			ConnectionTools.closeConnection(con);
		}
		
	}
	
	// 修改商品信息并保存
		public void UpdateSuppliers(Suppliers sup) throws SQLException {

			Connection con = null;
			try {
				con = ConnectionTools.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"update suppliers set suppliers_code=?,suppliers_name=?,suppliers_phone=? where suppliers_id =? ");

				ps.setString(1, sup.getSuppliers_code());
				ps.setString(2, sup.getSuppliers_name());
				ps.setString(3, sup.getSuppliers_phone());
				ps.setInt(4, sup.getSuppliers_id());
				
				// 向服务器发送命令
				ps.executeUpdate();

			} finally {
				// 关闭资源
				ConnectionTools.closeConnection(con);
			}
		}

}
