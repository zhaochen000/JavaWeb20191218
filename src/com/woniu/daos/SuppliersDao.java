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
			//���ؽ����
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				int suppliersID = res.getInt("suppliers_id");
				String suppliersCode = res.getString("suppliers_code");
				String suppliersName = res.getString("suppliers_name");
				String suppliersPhone = res.getString("suppliers_phone");
				//��װ��Suppliers���͵Ķ���
				Suppliers sup = new Suppliers(suppliersID,suppliersCode,suppliersName,suppliersPhone);
				//��ӵ�������ȥ
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
			//�������ݿ�
			con = ConnectionTools.getConnection();
			//�õ�����
			PreparedStatement ps = con.prepareStatement("insert into suppliers(suppliers_code,suppliers_name,suppliers_phone) value(?,?,?)");
		    //����ռλ����ֵ
			ps.setString(1, sup.getSuppliers_code());
			ps.setString(2, sup.getSuppliers_name());
            ps.setString(3, sup.getSuppliers_phone());	
            //���������������
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
		    //����ռλ��
			ps.setInt(1, suppDel);
	
			//���������������
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
			// �����ֻ��һ�м�¼�����Բ���ѭ��
			ResultSet res = ps.executeQuery();
			if (res.next()) {
				int suppliersId = res.getInt("suppliers_id");
				String suppliersCode = res.getString("suppliers_code");
				String suppliersName = res.getString("suppliers_name");
				String suppliersPhone = res.getString("suppliers_phone");
			    //��װ�ɶ���
			    sup = new Suppliers(suppliersId,suppliersCode,suppliersName,suppliersPhone);	
				
			}
			return sup;

		} finally {
			ConnectionTools.closeConnection(con);
		}
		
	}
	
	// �޸���Ʒ��Ϣ������
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
				
				// ���������������
				ps.executeUpdate();

			} finally {
				// �ر���Դ
				ConnectionTools.closeConnection(con);
			}
		}

}
