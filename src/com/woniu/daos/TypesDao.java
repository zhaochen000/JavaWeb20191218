package com.woniu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.woniu.beans.Types;
import com.woniu.tools.ConnectionTools;

public class TypesDao {

	public List<Types> getAllTypes() {
		Connection con = null;
		List<Types> list = new ArrayList<Types>();
		try {
			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from types");
			ResultSet res = ps.executeQuery();
			while (res.next()) {

				int typesId = res.getInt("types_id");
				String typesCode = res.getString("types_code");
				String typesName = res.getString("types_name");
				// 封装对象
				Types types = new Types(typesId, typesCode, typesName);

				list.add(types);

			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionTools.closeConnection(con);
		}

	}

	// 增加商品类型信息
	public void addTypes(Types types) throws SQLException {
		Connection con = null;
		try {
			con = ConnectionTools.getConnection();
			// 向数据库中添加信息
			PreparedStatement ps = con
					.prepareStatement("insert into types(types_id,types_code,types_name)values(?,?,?)");
			ps.setInt(1, types.getTypes_id());
			ps.setString(2, types.getTypes_code());
			ps.setString(3, types.getTypes_name());

			// 向服务器发送命令
			ps.executeUpdate();

		} finally {
			// 关闭资源
			ConnectionTools.closeConnection(con);

		}

	}

	// 删除商品类型信息
	public void deleteTypesById(int typesIDel) throws SQLException {
		Connection con = null;
		try {
			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from types where types_id=?");
			// 设置占位符与值
			ps.setInt(1, typesIDel);
			// 向服务器发送命令
			ps.executeUpdate();

		} finally {
			ConnectionTools.closeConnection(con);
		}

	}

	// 得到某个具体Id的商品类型信息
	public Types getTypesByID(int typesGin) throws SQLException {
		Connection con = null;
		Types types = null;
		try {
			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from types where types_id=?");
			ps.setInt(1, typesGin);
			// 结果集只有一行记录，可以不用循环
			ResultSet res = ps.executeQuery();
			if (res.next()) {
				int typesId = res.getInt("types_id");
				String typesCode = res.getString("types_code");
				String typesName = res.getString("types_Name");

				types = new Types(typesId, typesCode, typesName);

			}
			return types;

		} finally {
			ConnectionTools.closeConnection(con);
		}
	}

	// 修改保存商品类型信息
	public void UpdateTypes(Types t) throws SQLException {
		Connection con = null;
		try {
			con = ConnectionTools.getConnection();
			PreparedStatement ps = con.prepareStatement("update types set types_code=?,types_name=? where types_id =?");
			// 设置占位符和值

			ps.setString(1, t.getTypes_code());
			ps.setString(2, t.getTypes_name());
			ps.setInt(3, t.getTypes_id());

			// 向服务器发送命令
			ps.executeUpdate();

		} finally {
			// 关闭资源
			ConnectionTools.closeConnection(con);
		}

	}

}
