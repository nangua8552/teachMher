package com.lq.school.classe.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lq.school.classe.dao.dao.ClasseDAO;
import com.lq.school.classe.dao.vo.Classe;
import com.lq.school.teacher.dao.vo.Teacher;
import com.lq.school.util.DBUtil;

public class ClasseDAOImpl implements ClasseDAO {

	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	
	@Override
	public boolean add(Classe c) {

		String sql="insert into classe(name,college,tid) values(?,?,?)";
		try {
			
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1,c.getName());
			pst.setString(2,c.getCollege());
			pst.setInt(3,c.getT().getId());
			pst.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return false;
	}

	@Override
	public boolean update(Classe c) {
		String sql="update classe set name=?,college=?,tid=? where id=?";
		try {
			
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1,c.getName());
			pst.setString(2,c.getCollege());
			pst.setInt(3,c.getT().getId());
			pst.setInt(4, c.getId());
			pst.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return false;
	}

	@Override
	public boolean del(int id) {
		String sql="delete classe where id=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return false;
	}

	@Override
	public List<Classe> queryAll() {
		String sql="select c.*,t.name tname,t.* from classe c,teacher t where c.tid=t.id";
		ArrayList<Classe> clist = new ArrayList<Classe>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			
			rs=pst.executeQuery();
			System.out.println(rs);
			while(rs.next())
			{
				Teacher t =new Teacher();
				t.setAge(rs.getInt("age"));
				t.setId(rs.getInt("tid"));
				t.setLev(rs.getString("lev"));
				t.setName(rs.getString("tname"));
				t.setSex(rs.getString("sex"));
				
				Classe c=new Classe();
				c.setCollege(rs.getString("college"));
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setT(t);
				clist.add(c);
			}
			return clist;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return clist;
	}

	@Override
	public Classe queryById(int id) {
		String sql="select c.id,c.name cname,c.college,c.tid,t.age,t.lev,t.sex,t.name tname from classe c,teacher t where c.tid=t.id and c.id=?";

		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			
			if(rs.next())
			{
				Teacher t =new Teacher();
				t.setAge(rs.getInt("age"));
				t.setId(rs.getInt("tid"));
				t.setLev(rs.getString("lev"));
				t.setName(rs.getString("tname"));
				t.setSex(rs.getString("sex"));
				
				Classe c=new Classe();
				c.setCollege(rs.getString("college"));
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("cname"));
				c.setT(t);
				return c;
			}else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return null;
	}

	@Override
	public Classe queryByClasse(String Classe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		String sql="select count(id) from classe";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			if(rs.next())
			{
				int count =rs.getInt(1);
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return 0;
	}

	@Override
	public List<Classe> queryForPage(int page, int pageSize) {
		String sql="SELECT * FROM   "
				+ "	( SELECT A.*, ROWNUM RN   FROM        "
				+ "		 (select c.college,c.id,c.tid,t.age,t.lev,t.sex,c.name cname,t.name tname "
				+ "	from classe c , teacher t where c.tid= t.id ) A     "
				+ "		WHERE ROWNUM <= ? )    "//page*pageSize
				+ "	 WHERE RN > ?";//(page-1)*pageSize
		ArrayList<Classe> clist = new ArrayList<Classe>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, page*pageSize);
			pst.setInt(2, (page-1)*pageSize);
			rs=pst.executeQuery();			
			while(rs.next())
			{
				Teacher t = new Teacher();
				t.setId(rs.getInt("tid"));
				t.setAge(rs.getInt("age"));
				t.setLev(rs.getString("lev"));
				t.setName(rs.getString("tname"));
				t.setSex(rs.getString("sex"));
				Classe c=new Classe();
				c.setCollege(rs.getString("college"));
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("cname"));
				c.setT(t);
				clist.add(c);
			}
			return clist;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return clist;
	}

	@Override
	public List<Classe> queryForStudent() {
		String sql="select c.college,c.id,c.tid,t.age,t.lev,t.sex,c.name cname,t.name tname "
				+ " 	from classe c , teacher t where c.tid= t.id";
		ArrayList<Classe> clist = new ArrayList<Classe>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			
			while(rs.next())
			{
				Teacher t = new Teacher();
				t.setId(rs.getInt("tid"));
				t.setAge(rs.getInt("age"));
				t.setLev(rs.getString("lev"));
				t.setName(rs.getString("tname"));
				t.setSex(rs.getString("sex"));
				Classe c=new Classe();
				c.setCollege(rs.getString("college"));
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("cname"));
				c.setT(t);
				clist.add(c);
			}
			return clist;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return clist;
	}

}
