package com.lq.school.student.dao.vo;

import com.lq.school.classe.dao.vo.Classe;

public class Student {

     private String sno;
     private String name;
     private int age;
     private String sex;
     private String tel;
     private String address;
     private Classe c;
     
     
 	public Student() {
		super();
	}

	public Student(String sno, String name, int age, String sex, String tel,
			String address, Classe c) {
		super();
		this.sno = sno;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.tel = tel;
		this.address = address;
		this.c = c;
	}
     
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Classe getC() {
		return c;
	}
	public void setC(Classe c) {
		this.c = c;
	}

	
}
