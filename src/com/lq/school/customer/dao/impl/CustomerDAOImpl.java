package com.lq.school.customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lq.school.customer.dao.dao.CustomerDAO;
import com.lq.school.customer.dao.vo.Customer;
import com.lq.school.util.DBUtil;

public class CustomerDAOImpl implements CustomerDAO {
	
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public boolean add(Customer c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Customer c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean del(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Customer> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer queryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer queryByAccount(String account) {
		String sql="select * from customer where account = ?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, account);
			rs=pst.executeQuery();
			if(rs.next())
			{
				Customer c=new Customer();
				c.setAccount(rs.getString("account"));
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setPassword(rs.getString("password"));
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,pst,conn);
		};
		return null;
	}

}
