package com.lq.school.classe.dao.dao;

import java.util.List;

import com.lq.school.classe.dao.vo.Classe;

public interface ClasseDAO {

	public boolean add(Classe c);
	public boolean update(Classe c);
	public boolean del(int id);
	public List<Classe> queryAll();
	public Classe queryById(int id);
	public Classe queryByClasse(String Classe);
	public int count();//统计有多少条记录
	public List<Classe> queryForPage(int page,int pageSize);
	public List<Classe> queryForStudent();


}
