package com.lq.school.student.dao.factory;

import com.lq.school.student.dao.dao.StudentDAO;
import com.lq.school.student.dao.impl.StudentDAOImpl;

public class StudentDAOFactory {

	public static StudentDAO  getStudentDAO()
	{
		return new StudentDAOImpl();
	}
}
