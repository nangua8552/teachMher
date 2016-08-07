package com.lq.school.course.dao.dao;

import java.util.List;

import com.lq.school.course.dao.vo.Course;

public interface CourseDAO {

	public boolean add(Course c);
	public boolean update(Course c);
	public boolean del(int id);
	public List<Course> queryAll();
	public Course queryById(int id);
	public Course queryByName(String name);
	public int count();//统计有多少条记录
	public List<Course> queryForPage(int page,int pageSize);

}
