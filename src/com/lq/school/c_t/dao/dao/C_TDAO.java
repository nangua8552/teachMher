package com.lq.school.c_t.dao.dao;

import java.util.List;

import com.lq.school.c_t.dao.vo.C_T;

public interface C_TDAO {
	
	public boolean add(C_T ct);
	public boolean update(C_T ct);
	public boolean del(int tid);
	public C_T queryByCid(int cid);
	public C_T queryByTid(int tid);
	public List<C_T> queryAll() ;
	public List<C_T> queryForC_Ssctlist(int pageNo,int pageSize,String sno) ;
	public List<C_T> queryForC_Snctlist(int pageNo,int pageSize,String sno) ;
	public List<C_T> queryForC_Tsctlist(int pageNo,int pageSize,int cid) ;
	public List<C_T> queryForC_Tnctlist(int pageNo,int pageSize,int cid) ;
	public int count();//ͳ���ж�������¼
	public int countNo(String sno);//ͳ���ж�������¼
	public int countNoC(int cid);//ͳ���ж�������¼
	public List<C_T> queryForPage(int page,int pageSize);

}
