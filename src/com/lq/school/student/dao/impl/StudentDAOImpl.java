package com.lq.school.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lq.school.classe.dao.vo.Classe;
import com.lq.school.student.dao.dao.StudentDAO;
import com.lq.school.student.dao.vo.Student;
import com.lq.school.teacher.dao.vo.Teacher;
import com.lq.school.util.DBUtil;

public class StudentDAOImpl implements StudentDAO {

	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	
	@Override
	public boolean add(Student s) {
		String sql="insert into student(name,age,sex,tel,address,cid) values(?,?,?,?,?,?)";
		try {
			
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1,s.getName());
			pst.setInt(2,s.getAge());
			pst.setString(3, s.getSex());
			pst.setString(4, s.getTel());
			pst.setString(5, s.getAddress());
			pst.setInt(6, s.getC().getId());
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
	public boolean update(Student s) {
		String sql="update student set name=?,age=?,sex=?,tel=?,address=?,cid=? where sno=?";
		try {
			
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1,s.getName());
			pst.setInt(2, s.getAge());
			pst.setString(3,s.getSex());
			pst.setString(4, s.getTel());
			pst.setString(5, s.getAddress());
			pst.setInt(6, s.getC().getId());
			pst.setString(7, s.getSno());
			pst.executeUpdate();
			System.out.println(s.getName());
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return false;
	}

	@Override
	public boolean del(String sno) {
		String sql="delete student where sno=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, sno);
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
	public List<Student> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student queryBySno(String sno) {
		String sql="select s.sno,s.name sname,s.age,s.sex,s.tel,s.address,s.cid,c.name cname,c.college,c.tid from student s,classe c where s.cid=c.id and sno=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, sno);
			rs=pst.executeQuery();
			
			if(rs.next())
			{
				Student s=new Student();
				s.setAddress(rs.getString("address"));
				s.setAge(rs.getInt("age"));
				
				Classe c=new Classe();
				c.setCollege(rs.getString("college"));
				c.setId(rs.getInt("cid"));
				
				Teacher t=new Teacher();
				t.setId(rs.getInt("tid"));
				
				c.setT(t);
				c.setName(rs.getString("cname"));
				s.setC(c);
				s.setName(rs.getString("sname"));
				s.setSex(rs.getString("sex"));
				s.setSno(rs.getString("sno"));
				s.setTel(rs.getString("tel"));
				
				return s;
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
		String sql="select count(sno) from student";
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
	public List<Student> queryForPage(int page, int pageSize) {
		String sql="SELECT * FROM   "
				+ "	( SELECT A.*, ROWNUM RN   FROM        "
				+ "		 (select s.sno,s.name sname,s.age,s.sex,s.tel,s.address,s.cid,c.name cname,c.college,c.tid from student s,classe c where s.cid=c.id ) A "
				+ "		WHERE ROWNUM <= ? )    "//page*pageSize
				+ "	 WHERE RN > ?";//(page-1)*pageSize
		ArrayList<Student> slist = new ArrayList<Student>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, page*pageSize);
			pst.setInt(2, (page-1)*pageSize);
			rs=pst.executeQuery();			
			while(rs.next())
			{
				Student s=new Student();
				s.setAddress(rs.getString("address"));
				s.setAge(rs.getInt("age"));
				
				Classe c=new Classe();
				c.setCollege(rs.getString("college"));
				c.setId(rs.getInt("cid"));
				
				Teacher t=new Teacher();
				t.setId(rs.getInt("tid"));
				
				c.setT(t);
				c.setName(rs.getString("cname"));
				s.setC(c);
				s.setName(rs.getString("sname"));
				s.setSex(rs.getString("sex"));
				s.setSno(rs.getString("sno"));
				s.setTel(rs.getString("tel"));
				slist.add(s);
			}
			return slist;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return slist;
	}

}
