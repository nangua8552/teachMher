package com.lq.school.c_s.dao.factory;

import com.lq.school.c_s.dao.dao.C_SDAO;
import com.lq.school.c_s.dao.impl.C_SDAOImpl;

public class C_SDAOFactory {
	
	public static C_SDAO getC_SDAO()
	{
		return new C_SDAOImpl();
	}

}
