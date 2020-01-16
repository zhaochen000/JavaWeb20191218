package com.woniu.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;

public class ConnectionTools {

	private static final String DRIVERCLASS;
	private static final String CONNECTIONURL;
	private static final String USERNAME;
	private static final String PASSWORD;
	private static final int MAXACTIVE;
	private static final int MINIDLE;
	private static final int MAXWAIT;
	// 得到数据源（连接池的具体实现）
	private static final DruidDataSource dataSource;

	static {
		Properties prop = new Properties();
		String path = ConnectionTools.class.getClassLoader().getResource("/").getPath();
		File f = new File(path + "jdbc.properties");
			try {
				InputStream reader = new FileInputStream(f);
				prop.load(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}

		// 根据键获得值
		DRIVERCLASS = prop.getProperty("driver");
		CONNECTIONURL = prop.getProperty("url");
		USERNAME = prop.getProperty("user");
		PASSWORD = prop.getProperty("password");
		MAXACTIVE = Integer.parseInt(prop.getProperty("maxActive"));
		MINIDLE = Integer.parseInt(prop.getProperty("minIdle"));

		MAXWAIT = Integer.parseInt(prop.getProperty("maxWait"));
		// 创建数据源（连接池）
		dataSource = new DruidDataSource();
		// 设置驱动程序字符串
		dataSource.setDriverClassName(DRIVERCLASS);// 设置驱动程序字符串
		dataSource.setUrl(CONNECTIONURL);// 设置连接字符串
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		// 设置最大连接数
		dataSource.setMaxActive(MAXACTIVE);
		// 设置最小连接数
		dataSource.setMinIdle(MINIDLE);
		// 设置最大连接等待时间
		dataSource.setMaxWait(MAXWAIT);

	}

	public static Connection getConnection() {

		Connection conn=null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection(Connection con) {
		try {
			if(con!=null&&!con.isClosed()){
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
