package com.lq.school.teacher.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lq.school.teacher.dao.dao.TeacherDAO;
import com.lq.school.teacher.dao.vo.Teacher;
import com.lq.school.util.DBUtil;

public class TeacherDAOImpl implements TeacherDAO {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	
	@Override
	public boolean add(Teacher t) {
		
		String sql="insert into teacher(name,age,sex,lev) values(?,?,?,?)";
		try {
			
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1,t.getName());
			pst.setInt(2,t.getAge());
			pst.setString(3,t.getSex());
			pst.setString(4,t.getLev());
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
	public boolean update(Teacher t) {
		
		String sql="update teacher set name=?,age=?,sex=?,lev=? where id=?";
		try {
			
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1,t.getName());
			pst.setInt(2,t.getAge());
			pst.setString(3,t.getSex());
			pst.setString(4,t.getLev());
			pst.setInt(5,t.getId());
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
		String sql="delete teacher where id=?";
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
	public List<Teacher> queryAll() {
		String sql="select * from teacher";
		ArrayList<Teacher> tlist = new ArrayList<Teacher>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			
			while(rs.next())
			{
				Teacher t = new Teacher();
				t.setAge(rs.getInt("age"));
				t.setId(rs.getInt("id"));
				t.setLev(rs.getString("lev"));
				t.setName(rs.getString("name"));
				t.setSex(rs.getString("sex"));
				tlist.add(t);
			}
			return tlist;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return tlist;
	}

	@Override
	public Teacher queryById(int id) {
		String sql="select * from teacher where id=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			
			if(rs.next())
			{
				Teacher t = new Teacher();
				t.setAge(rs.getInt("age"));
				t.setId(rs.getInt("id"));
				t.setLev(rs.getString("lev"));
				t.setName(rs.getString("name"));
				t.setSex(rs.getString("sex"));
				return t;
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
	public Teacher queryByTeacher(String Teacher) {
		return null;
	}

	@Override
	public int count() {
		String sql="select count(id) from teacher";
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
	public List<Teacher> queryForPage(int page, int pageSize) {

		String sql="SELECT * FROM "
				+ "  ( SELECT A.*, ROWNUM RN   FROM "
				+ "  (SELECT * FROM teacher) A  "
				+ "  WHERE ROWNUM <= ? )"//page*pageSize
				+ "   WHERE RN > ?";//(page-1)*pageSize
		ArrayList<Teacher> tlist = new ArrayList<Teacher>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, page*pageSize);
			pst.setInt(2, (page-1)*pageSize);
			rs=pst.executeQuery();			
			while(rs.next())
			{
				Teacher t = new Teacher();
				t.setAge(rs.getInt("age"));
				t.setId(rs.getInt("id"));
				t.setLev(rs.getString("lev"));
				t.setName(rs.getString("name"));
				t.setSex(rs.getString("sex"));
				tlist.add(t);
			}
			return tlist;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return tlist;
	}

	@Override
	public List<Teacher> queryForClasse() {
		String sql="select * from teacher where id not in (select tid from classe)";
		ArrayList<Teacher> tlist = new ArrayList<Teacher>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			
			while(rs.next())
			{
				Teacher t = new Teacher();
				t.setAge(rs.getInt("age"));
				t.setId(rs.getInt("id"));
				t.setLev(rs.getString("lev"));
				t.setName(rs.getString("name"));
				t.setSex(rs.getString("sex"));
				tlist.add(t);
			}
			return tlist;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return tlist;
	}

	
	
	@Override
	public List<Teacher> queryForCourseN(int cid) {
		String sql="select * from teacher where id not in (select tid from c_t where cid=?)";
		ArrayList<Teacher> tlist = new ArrayList<Teacher>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs=pst.executeQuery();
			
			while(rs.next())
			{
				Teacher t = new Teacher();
				t.setAge(rs.getInt("age"));
				t.setId(rs.getInt("id"));
				t.setLev(rs.getString("lev"));
				t.setName(rs.getString("name"));
				t.setSex(rs.getString("sex"));
				tlist.add(t);
			}
			return tlist;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return tlist;
	}

	@Override
	public int countNo() {
		String sql="select count(id) from teacher where id not in (select tid from c_t)";
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
}
