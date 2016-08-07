package com.lq.school.c_t.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lq.school.c_t.dao.dao.C_TDAO;
import com.lq.school.c_t.dao.vo.C_T;
import com.lq.school.course.dao.dao.CourseDAO;
import com.lq.school.course.dao.factory.CourseDAOFactory;
import com.lq.school.course.dao.vo.Course;
import com.lq.school.teacher.dao.dao.TeacherDAO;
import com.lq.school.teacher.dao.factory.TeacherDAOFactory;
import com.lq.school.teacher.dao.vo.Teacher;
import com.lq.school.util.DBUtil;

public class C_TDAOImpl implements C_TDAO {

	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	
	private CourseDAO cdao=CourseDAOFactory.getCourseDAO();
	private TeacherDAO tdao=TeacherDAOFactory.getTeacherDAO();
	
	@Override
	public boolean add(C_T ct) {

		String sql="insert into c_t values(?,?)";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, ct.getC().getId());
			pst.setInt(2, ct.getT().getId());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return false;
	}

	@Override
	public boolean update(C_T ct) {
		String sql="update c_t set tid=? where cid=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, ct.getT().getId());
			pst.setInt(2, ct.getC().getId());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return false;
	}

	@Override
	public boolean del(int tid) {
		String sql="delete c_t where tid=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, tid);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return false;
	}

	@Override
	public C_T queryByCid(int cid) {
		String sql="select * from c_t where cid=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs=pst.executeQuery();
			if(rs.next()){
				C_T ct=new C_T();
				Course c=new Course();
				c.setId(cid);
				ct.setC(c);
				Teacher t=tdao.queryById((rs.getInt("tid")));
				ct.setT(t);
				return ct;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return null;
	}

	@Override
	public C_T queryByTid(int tid) {
		String sql="select * from c_t where tid=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, tid);
			rs=pst.executeQuery();
			if(rs.next()){
				C_T ct=new C_T();
				Course c=cdao.queryById((rs.getInt("cid")));
				ct.setC(c);
				Teacher t=new Teacher();
				t.setId(tid);
				ct.setT(t);
				return ct;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return null;
	}

	@Override
	public int count() {
		String sql="select count(cid) from c_t";
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
	public int countNo(String sno) {
		String sql="select count(cid) from c_t where cid not in (select cid from c_s where sid<>?) ";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, sno);
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
	public List<C_T> queryForPage(int page, int pageSize) {
		String sql="SELECT * FROM "
				+ "  ( SELECT A.*, ROWNUM RN   FROM "
				+ "  (SELECT * FROM c_t) A  "
				+ "  WHERE ROWNUM <= ? )"//page*pageSize
				+ "   WHERE RN > ?";//(page-1)*pageSize
		ArrayList<C_T> ctlist = new ArrayList<C_T>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, page*pageSize);
			pst.setInt(2, (page-1)*pageSize);
			rs=pst.executeQuery();			
			while(rs.next())
			{
				C_T ct=new C_T();
				Course c=new Course();
				c.setId(rs.getInt("cid"));
				ct.setC(c);
				Teacher t=new Teacher();
				t.setId(rs.getInt("tid"));
				ct.setT(t);
				ctlist.add(ct);
			}
			return ctlist;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return ctlist;
	}

	@Override
	public List<C_T> queryAll() {
		String sql="select * from c_t";
		ArrayList<C_T> ctlist = new ArrayList<C_T>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			
			while(rs.next())
			{
				C_T ct=new C_T();
				Course c=cdao.queryById(rs.getInt("cid"));
				ct.setC(c);
				Teacher t=tdao.queryById(rs.getInt("tid"));
				ct.setT(t);
				ctlist.add(ct);
				
			}
			return ctlist;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return ctlist;
	}

	@Override
	public List<C_T> queryForC_Ssctlist(int pageNo,int pageSize,String sno){
		String sql="SELECT * FROM "
				+ "  ( SELECT A.*, ROWNUM RN   FROM "
				+ "  (select * from c_t where cid  in (select cid from c_s where sid=?)) A  "
				+ "  WHERE ROWNUM <= ? )"//page*pageSize
				+ "   WHERE RN > ?";//(page-1)*pageSize
		ArrayList<C_T> sctlist = new ArrayList<C_T>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, sno);
			pst.setInt(2, pageNo*pageSize);
			pst.setInt(3, (pageNo-1)*pageSize);
			rs=pst.executeQuery();			
			while(rs.next())
			{
				C_T ct=new C_T();
				Course c=cdao.queryById(rs.getInt("cid"));
				ct.setC(c);
				Teacher t=tdao.queryById(rs.getInt("tid"));
				ct.setT(t);
				sctlist.add(ct);
			}
			return sctlist;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return sctlist;
	}

	@Override
	public List<C_T> queryForC_Snctlist(int page,int pageSize,String sno) {
		String sql="select * from (select a.*,rownum rn from "
				+ "  (select * from c_t where cid not in (select cid from c_s where sid=?)) a"
				+ " where rownum <=?)"
				+ " where rn>?";
		ArrayList<C_T> nctlist = new ArrayList<C_T>();
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, sno);
			pst.setInt(2, page*pageSize);
			pst.setInt(3, (page-1)*pageSize);
			rs=pst.executeQuery();			
			while(rs.next())
			{
				C_T ct=new C_T();
				Course c=cdao.queryById(rs.getInt("cid"));
				ct.setC(c);
				Teacher t=tdao.queryById(rs.getInt("tid"));
				ct.setT(t);
				nctlist.add(ct);
			}
			return nctlist;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, pst, conn);
		}
		return nctlist;
	}

	@Override
	public List<C_T> queryForC_Tsctlist(int pageNo, int pageSize, int cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<C_T> queryForC_Tnctlist(int pageNo, int pageSize, int cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countNoC(int cid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
