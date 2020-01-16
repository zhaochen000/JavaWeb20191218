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
	// �õ�����Դ�����ӳصľ���ʵ�֣�
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

		// ���ݼ����ֵ
		DRIVERCLASS = prop.getProperty("driver");
		CONNECTIONURL = prop.getProperty("url");
		USERNAME = prop.getProperty("user");
		PASSWORD = prop.getProperty("password");
		MAXACTIVE = Integer.parseInt(prop.getProperty("maxActive"));
		MINIDLE = Integer.parseInt(prop.getProperty("minIdle"));

		MAXWAIT = Integer.parseInt(prop.getProperty("maxWait"));
		// ��������Դ�����ӳأ�
		dataSource = new DruidDataSource();
		// �������������ַ���
		dataSource.setDriverClassName(DRIVERCLASS);// �������������ַ���
		dataSource.setUrl(CONNECTIONURL);// ���������ַ���
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		// �������������
		dataSource.setMaxActive(MAXACTIVE);
		// ������С������
		dataSource.setMinIdle(MINIDLE);
		// ����������ӵȴ�ʱ��
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
