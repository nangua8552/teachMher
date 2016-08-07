package com.lq.school.student.dao.dao;

import java.util.List;

import com.lq.school.student.dao.vo.Student;

public interface StudentDAO {
	
	public boolean add(Student s);
	public boolean update(Student s);
	public boolean del(String sno);
	public List<Student> queryAll();
	public Student queryBySno(String sno);
	public int count();//统计有多少条记录
	public List<Student> queryForPage(int page,int pageSize);
}
