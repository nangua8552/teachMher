package com.lq.school.teacher.dao.dao;

import java.util.List;

import com.lq.school.teacher.dao.vo.Teacher;

public interface TeacherDAO {
	
	public boolean add(Teacher c);
	public boolean update(Teacher c);
	public boolean del(int id);
	public List<Teacher> queryAll();
	public Teacher queryById(int id);
	public Teacher queryByTeacher(String teacher);//��Ϣ(password)   null
	public int count();//ͳ���ж�������¼
	public int countNo();//ͳ���ж�������¼
	public List<Teacher> queryForPage(int page,int pageSize);
	public List<Teacher> queryForClasse();
	public List<Teacher> queryForCourseN(int cid);
}
