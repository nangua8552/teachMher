package com.lq.school.teacher.dao.factory;

import com.lq.school.teacher.dao.dao.TeacherDAO;
import com.lq.school.teacher.dao.impl.TeacherDAOImpl;

public class TeacherDAOFactory {
	
	public static TeacherDAO  getTeacherDAO()
	{
		return new TeacherDAOImpl();
	}

}
