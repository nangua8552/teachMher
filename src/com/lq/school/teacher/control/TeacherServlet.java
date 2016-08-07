package com.lq.school.teacher.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.school.teacher.dao.dao.TeacherDAO;
import com.lq.school.teacher.dao.factory.TeacherDAOFactory;
import com.lq.school.teacher.dao.vo.Teacher;

public class TeacherServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//如何变量-->线程安全
	private TeacherDAO tdao=TeacherDAOFactory.getTeacherDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String op=req.getParameter("op");
		if("add".equals(op))
		{//添加
			add(req,resp);
		}else if("tlist".equals(op))
		{//列表
			list(req,resp);
		}else if("del".equals(op))
		{//删除
			del(req,resp);
		}else if("toUpdate".equals(op))
		{//to更新
			toUpdate(req,resp);
		}else if("update".equals(op))
		{//更新
			update(req,resp);
		}else{
			System.out.println("未知操作："+op);
		}
	}
	
	/**
	 * 更新方法
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String sex=request.getParameter("sex");
		String lev=request.getParameter("lev");
		String id=request.getParameter("id");
		Teacher t=new Teacher();
		t.setAge(Integer.parseInt(age));
		t.setLev(lev);
		t.setName(name);
		t.setSex(sex);
		t.setId(Integer.parseInt(id));
		boolean b=tdao.update(t);
		if (b) {
			out.print("<script type='text/javascript'>alert('更新老师成功！');location='TeacherServlet?op=tlist';</script>");
			//response.sendRedirect("TeacherServlet?op=tlist");
		}else{
			out.print("<script type='text/javascript'>alert('更新老师失败！');history.back();</script>");

		}
	}

	/**
	 * to更新
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		PrintWriter out=resp.getWriter();
		String id=req.getParameter("id");
		Teacher t=tdao.queryById(Integer.parseInt(id));
		if(t!=null)
		{
			req.setAttribute("t", t);
			req.getRequestDispatcher("updateteacher.jsp")
					.forward(req, resp);
		}else{
			out.print("<script type='text/javascript'>alert('此老师不存在！');location='TeacherServlet?op=tlist';</script>");
		}
	}

	/**
	 * 删除方法
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void del(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter out=resp.getWriter();
		String id=req.getParameter("id");
		boolean b=tdao.del(Integer.parseInt(id));
		if(b)
		{
			out.print("<script type='text/javascript'>alert('删除老师成功！');location='TeacherServlet?op=tlist';</script>");
			//resp.sendRedirect("TeacherServlet?op=tlist");
		}else{
			out.print("<script type='text/javascript'>alert('删除老师失败！');location='TeacherServlet?op=tlist';</script>");
			//resp.sendRedirect("TeacherServlet?op=tlist");
			
		}
	}

	/**
	 * 列表方法
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url="TeacherServlet?op=tlist";
		String no=req.getParameter("pageNo");
		int pageNo=no==null?1:Integer.parseInt(no);
		int pageSize=5;
		int recordCount=tdao.count();
		
		List<Teacher> tlist=tdao.queryForPage(pageNo, pageSize);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("recordCount", recordCount);
		req.setAttribute("url", url);
		req.setAttribute("tlist", tlist);
		req.getRequestDispatcher("teacher.jsp")
								.forward(req, resp);
	}

	/**
	 * 添加方法
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String sex=request.getParameter("sex");
		String lev=request.getParameter("lev");
		Teacher t=new Teacher();
		t.setAge(Integer.parseInt(age));
		t.setLev(lev);
		t.setName(name);
		t.setSex(sex);
		boolean b=tdao.add(t);
		if (b) {
			out.print("<script type='text/javascript'>alert('添加老师成功！');location='TeacherServlet?op=tlist';</script>");
			//response.sendRedirect("TeacherServlet?op=tlist");
		}else{
			out.print("<script type='text/javascript'>alert('添加老师失败！');history.back();</script>");

		}
	}

}
