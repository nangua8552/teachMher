package com.lq.school.customer.dao.dao;

import java.util.List;

import com.lq.school.customer.dao.vo.Customer;

public interface CustomerDAO {
	
	public boolean add(Customer c);
	public boolean update(Customer c);
	public boolean del(int id);
	public List<Customer> queryAll();
	public Customer queryById(int id);
	public Customer queryByAccount(String account);//пео╒(password)   null

}
