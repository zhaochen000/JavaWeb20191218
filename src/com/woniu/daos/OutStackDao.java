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
    //��ѯ���еĳ��ⵥ��Ϣ
	public List<OutStack> getAllOutStack(){
		
		Connection conn = null;
		List<OutStack> list = new ArrayList<OutStack>();
		try {
			conn = ConnectionTools.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from outStack");
			//���ؽ����
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
				//��װ�ɶ���
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
			// ����ʾռλ
			PreparedStatement p = con.prepareStatement("insert into outStack(goods_id,user_id,outStack_code,outStack_count,outStack_time,outStack_status,outStack_remarks)values(?,?,?,?,now(),'δȷ��',?)");
			p.setInt(1,outStack.getGoods_id());
			p.setInt(2, outStack.getUser_id());
			p.setString(3,outStack.getOutStack_code());
			p.setInt(4, outStack.getOutStack_count());
			p.setString(5, outStack.getOutStack_remarks());
			// ���������������
			p.executeUpdate();

		} finally {
			// �ر���Դ
			ConnectionTools.closeConnection(con);

		}

		
	}

	public void deleteOutStackById(int outStackDel) throws SQLException {
		Connection con = null;
		try {
			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from outStack where outStack_id=?");
			// ����ռλ��
			ps.setInt(1, outStackDel);
			// ���������������
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
			// �����ֻ��һ�м�¼�����Բ���ѭ��
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
            //����ռλ����ֵ
			ps.setInt(1, outStack.getGoods_id());
			ps.setInt(2,outStack.getUser_id());
			ps.setString(3, outStack.getOutStack_code());
			ps.setFloat(4,outStack.getOutStack_count());
			ps.setString(5,outStack.getOutStack_remarks());
			ps.setInt(6, outStack.getOutStack_id());
			
			// ���������������
			ps.executeUpdate();

		} finally {
			// �ر���Դ
			ConnectionTools.closeConnection(con);
		}
		
	}
    //�������
	public void confirmInStack(OutStack outStack) throws SQLException {
		
		Connection conn=null;
		try {
			conn=ConnectionTools.getConnection();
			//�Գ��ⵥ��״̬�����޸�
			PreparedStatement ps=conn.prepareStatement("update outStack set outStack_status='��ȷ��' where outStack_id=?");
			ps.setInt(1, outStack.getOutStack_id());
			ps.executeUpdate();
			
			//�������
			ps=conn.prepareStatement("update goods set goods_count=goods_count-? where goods_id=?");
			ps.setInt(1, outStack.getOutStack_count());
			ps.setInt(2, outStack.getGoods_id());
			ps.executeUpdate();
			
		} 
		finally{
			ConnectionTools.closeConnection(conn);
			
		}	
		
	}
    //����ѯ�����ķ���
	public List<OutStack> getAllOutStack(String outStackCode, String goodsName) throws SQLException {
		Connection conn=null;
		List<OutStack> outStackList=new ArrayList<OutStack>();
		try {
			conn=ConnectionTools.getConnection();
		    //ƴ��sql��䣬������ϲ�ѯ
			String sql="select outStack_id,outStack.goods_id,user_id,outStack_code,outStack_count,outStack_time,outStack_status,outStack_remarks,goods_name from outStack left join goods on outStack.goods_id=goods.goods_id where 1=1 ";
			//ƴ�Ӵ���ѯ������sql���
			if(outStackCode!=null && !outStackCode.equals("")){
				sql = sql + " and outStack_code like ?";
				
			}
			
			if(goodsName!=null && !goodsName.equals("")){
				
				sql = sql + " and goods_name like ?";
			}
	
			//��sql�����б���
			PreparedStatement ps=conn.prepareStatement(sql);
			
			int count=0;
			//ռλ���ĸ�����ȷ�������ü�����
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
		    	//����Ҫ��ѯ����Ʒ���Ƶ�ֵ
		    	Goods g=new Goods();
		    	g.setGoods_name(goods_name);
		    	//��װ�ɶ���
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
		    //ƴ��sql��䣬������ϲ�ѯ
			String sql="select outStack_id,outStack.goods_id,user_id,outStack_code,outStack_count,outStack_time,outStack_status,outStack_remarks,goods_name from outStack left join goods on outStack.goods_id=goods.goods_id where 1=1 ";
			//ƴ�Ӵ���ѯ������sql���
			if(outStackCode!=null && !outStackCode.equals("")){
				sql = sql + " and outStack_code like ?";
				
			}
			
			if(goodsName!=null && !goodsName.equals("")){
				
				sql = sql + " and goods_name like ?";
			}
			
			List<OutStack> outStackList=new ArrayList<OutStack>();
			
			sql = sql + " limit " + (pab.getCurrentPage()-1)*pab.getPageSize() +"," + pab.getPageSize();
			
			//��sql�����б���
			PreparedStatement ps=conn.prepareStatement(sql);
			
			int count=0;
			//ռλ���ĸ�����ȷ�������ü�����
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
		    	//����Ҫ��ѯ����Ʒ���Ƶ�ֵ
		    	Goods g=new Goods();
		    	g.setGoods_name(goods_name);
		    	//��װ�ɶ���
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
		    //ƴ��sql��䣬������ϲ�ѯ
			String sql="select count(outStack_id) as 'ok' from outStack left join goods on outStack.goods_id=goods.goods_id where 1=1 ";
			//ƴ�Ӵ���ѯ������sql���
			if(outStackCode!=null && !outStackCode.equals("")){
				sql = sql + " and outStack_code like ?";
				
			}
			
			if(goodsName!=null && !goodsName.equals("")){
				
				sql = sql + " and goods_name like ?";
			}
		
			
			//��sql�����б���
			PreparedStatement ps=conn.prepareStatement(sql);
			
			int count=0;
			//ռλ���ĸ�����ȷ�������ü�����
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
