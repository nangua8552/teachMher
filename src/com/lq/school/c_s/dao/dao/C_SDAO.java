package com.lq.school.c_s.dao.dao;

import java.util.List;

import com.lq.school.c_s.dao.vo.C_S;

public interface C_SDAO {

	public boolean add(C_S cs);
	public boolean update(C_S cs);
	public boolean del(int cid);
	public C_S queryBySno(String sno);
	public C_S queryByCid(int cid);
	public C_S queryByC_S(C_S cs);
	public int count(String sno);//统计有多少条记录
	public List<C_S> queryForPage(int page,int pageSize);

}
