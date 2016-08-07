package com.lq.school.c_t.dao.factory;

import com.lq.school.c_t.dao.dao.C_TDAO;
import com.lq.school.c_t.dao.impl.C_TDAOImpl;

public class C_TDAOFactory {

	public static C_TDAO getC_TDAO()
	{
		return new C_TDAOImpl();
	}
}
