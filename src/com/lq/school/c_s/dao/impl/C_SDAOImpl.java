package com.lq.school.c_s.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lq.school.c_s.dao.dao.C_SDAO;
import com.lq.school.c_s.dao.vo.C_S;
import com.lq.school.course.dao.vo.Course;
import com.lq.school.util.DBUtil;

public class C_SDAOImpl implements C_SDAO {

	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	
	@Override
	public boolean add(C_S cs) {

		String sql="insert into c_s values(?,?)";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, cs.getS().getSno());
			pst.setInt(2, cs.getC().getId());			
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
	public boolean update(C_S cs) {

		String sql="update c_s set cid=? where sno=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cs.getC().getId());
			pst.setString(2, cs.getS().getSno());
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
	public boolean del(int cid) {

		String sql="delete c_s where cid=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public C_S queryByCid(int cid) {

		String sql="select * from c_s where cid=?";
		try {
			conn=DBUtil.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs=pst.executeQuery();
			if(rs.next()){
				C_S cs=new C_S();
				Course c=new Course();
				c.setId(cid);
				cs.setC(c); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public C_S queryByC_S(C_S cs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(String sno) {
		String sql="select count(cid) from c_s where sid=?";
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
	public List<C_S> queryForPage(int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public C_S queryBySno(String sno) {
		// TODO Auto-generated method stub
		return null;
	}

}
