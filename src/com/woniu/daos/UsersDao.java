package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.woniu.beans.Users;
import com.woniu.tools.ConnectionTools;

public class UsersDao {
	
	public int isExit(Users u) throws SQLException{
		Connection con = null;
		try {
			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from users where user_name=? and user_pwd=?");
			ps.setString(1, u.getUser_name());
			ps.setString(2, u.getUser_pwd());
			
			ResultSet res = ps.executeQuery();
			if (res.next()) {
				return res.getInt("user_id");
			}else{
				return 0;
			}
		
		} finally {
			ConnectionTools.closeConnection(con);
		}	
	}
  
}
