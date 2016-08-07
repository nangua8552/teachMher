package com.lq.school.course.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lq.school.course.dao.dao.CourseDAO;
import com.lq.school.course.dao.vo.Course;
import com.lq.school.util.DBUtil;

public class CourseDAOImpl implements CourseDAO {

	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	
	@Override
	public boolean add(Course c) {
		String sql="insert into course(name,credit) values(?,?)";
		try {
			
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1,c.getName());
			pst.setInt(2,c.getCredit());
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
	public boolean update(Course c) {
		String sql="update course set name=?,credit=? where id=?";
		try {
			
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1,c.getName());
			pst.setInt(2,c.getCredit());
			pst.setInt(3, c.getId());
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
		String sql="delete course where id=?";
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
	public List<Course> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course queryById(int id) {
		String sql="select * from course where id=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			
			if(rs.next())
			{
				Course c=new Course();
				c.setName(rs.getString("name"));
				c.setCredit(rs.getInt("credit"));
				c.setId(id);
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
	public int count() {
		String sql="select count(id) from course";
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
	public List<Course> queryForPage(int page, int pageSize) {
		String sql="SELECT * FROM "
				+ "  ( SELECT A.*, ROWNUM RN   FROM "
				+ "  (SELECT * FROM course) A  "
				+ "  WHERE ROWNUM <= ? )"//page*pageSize
				+ "   WHERE RN > ?";//(page-1)*pageSize
		ArrayList<Course> clist = new ArrayList<Course>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, page*pageSize);
			pst.setInt(2, (page-1)*pageSize);
			rs=pst.executeQuery();			
			while(rs.next())
			{
				Course c=new Course();
				c.setCredit(rs.getInt("credit"));
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
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
	public Course queryByName(String name) {
		String sql="select * from course where name=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			rs=pst.executeQuery();
			
			if(rs.next())
			{
				Course c=new Course();
				c.setName(rs.getString("name"));
				c.setCredit(rs.getInt("credit"));
				c.setId(rs.getInt("id"));
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

}
