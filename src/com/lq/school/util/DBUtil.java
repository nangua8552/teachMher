package com.lq.school.util;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;

/**
 * 在java中，编写数据库连接池需实现java.sql.DataSource接口，
 * 每一种数据库连接池都是DataSource接口的实现
 * DBCP连接池就是java.sql.DataSource接口的一个具体实现
 */
public class DBUtil {

	private static DataSource ds = null;
	// 在静态代码块中创建数据库连接池
	static {
		try {
			// 加载dbcp.properties配置文件
			InputStream in = DBUtil.class.getClassLoader()
					.getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);
			// 创建数据源
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 从数据源中获取数据库连接
	public static Connection getConnection() throws SQLException {
		// 从数据源中获取数据库连接
		return ds.getConnection();
	}

	public static void close(ResultSet rs,PreparedStatement pst,Connection conn)
	{
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pst!=null)
		{
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}