package com.lq.school.customer.dao.factory;

import com.lq.school.customer.dao.dao.CustomerDAO;
import com.lq.school.customer.dao.impl.CustomerDAOImpl;

public class CustomerDAOFactory {
	
	public static CustomerDAO getCustomerDAO()
	{
		return new CustomerDAOImpl();
	}

}
