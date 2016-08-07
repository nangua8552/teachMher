package com.lq.school.course.dao.factory;

import com.lq.school.course.dao.dao.CourseDAO;
import com.lq.school.course.dao.impl.CourseDAOImpl;

public class CourseDAOFactory {

	public static CourseDAO getCourseDAO()
	{
		return new CourseDAOImpl();
	}
}
