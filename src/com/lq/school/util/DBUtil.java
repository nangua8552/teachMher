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
 * ��java�У���д���ݿ����ӳ���ʵ��java.sql.DataSource�ӿڣ�
 * ÿһ�����ݿ����ӳض���DataSource�ӿڵ�ʵ��
 * DBCP���ӳؾ���java.sql.DataSource�ӿڵ�һ������ʵ��
 */
public class DBUtil {

	private static DataSource ds = null;
	// �ھ�̬������д������ݿ����ӳ�
	static {
		try {
			// ����dbcp.properties�����ļ�
			InputStream in = DBUtil.class.getClassLoader()
					.getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);
			// ��������Դ
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ������Դ�л�ȡ���ݿ�����
	public static Connection getConnection() throws SQLException {
		// ������Դ�л�ȡ���ݿ�����
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