package com.lq.school.teacher.dao.dao;

import java.util.List;

import com.lq.school.teacher.dao.vo.Teacher;

public interface TeacherDAO {
	
	public boolean add(Teacher c);
	public boolean update(Teacher c);
	public boolean del(int id);
	public List<Teacher> queryAll();
	public Teacher queryById(int id);
	public Teacher queryByTeacher(String teacher);//信息(password)   null
	public int count();//统计有多少条记录
	public int countNo();//统计有多少条记录
	public List<Teacher> queryForPage(int page,int pageSize);
	public List<Teacher> queryForClasse();
	public List<Teacher> queryForCourseN(int cid);
}
