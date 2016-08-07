package com.lq.school.classe.dao.factory;

import com.lq.school.classe.dao.dao.ClasseDAO;
import com.lq.school.classe.dao.impl.ClasseDAOImpl;

public class ClasseDAOFactory {

	public static ClasseDAO getClasseDAO()
	{
		return new ClasseDAOImpl();
	}
}
