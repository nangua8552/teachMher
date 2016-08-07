package com.lq.school.classe.dao.vo;

import com.lq.school.teacher.dao.vo.Teacher;

public class Classe {

     private int id;
	 private String name;
	 private String college;
	 private Teacher t;
	 
	public Classe() {
		super();
	}
	public Classe(int id, String name, String college, Teacher t) {
		super();
		this.id = id;
		this.name = name;
		this.college = college;
		this.t = t;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public Teacher getT() {
		return t;
	}
	public void setT(Teacher t) {
		this.t = t;
	}
	 
	
}
