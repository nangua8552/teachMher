package com.lq.school.c_t.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.school.c_t.dao.dao.C_TDAO;
import com.lq.school.c_t.dao.factory.C_TDAOFactory;
import com.lq.school.c_t.dao.vo.C_T;
import com.lq.school.course.dao.dao.CourseDAO;
import com.lq.school.course.dao.factory.CourseDAOFactory;
import com.lq.school.course.dao.vo.Course;
import com.lq.school.teacher.dao.dao.TeacherDAO;
import com.lq.school.teacher.dao.factory.TeacherDAOFactory;
import com.lq.school.teacher.dao.vo.Teacher;

/**
 * Servlet implementation class C_TServlet
 */
@WebServlet("/C_TServlet")
public class C_TServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private C_TDAO ctdao=C_TDAOFactory.getC_TDAO();
	private TeacherDAO tdao=TeacherDAOFactory.getTeacherDAO();
	private CourseDAO cdao=CourseDAOFactory.getCourseDAO();
	
	public C_TServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String op=request.getParameter("op");
		if("add".equals(op))
		{//添加
			add(request,response);
		}else if("list".equals(op))
		{//列表
			list(request,response);
		}else if("del".equals(op))
		{//删除
			del(request,response);
		}else{
			System.out.println("未知操作："+op);
		}
	}


	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter out=response.getWriter();
		String cid=request.getParameter("cid");
		String tid=request.getParameter("tid");
		Course c=new Course();
		c.setId(Integer.parseInt(cid));
		Teacher t=new Teacher();
		t.setId(Integer.parseInt(tid));
		C_T ct=new C_T();
		ct.setC(c);
		ct.setT(t);
		
		boolean b=ctdao.add(ct);
		if (b) {
			out.print("<script type='text/javascript'>alert('指派老师成功！');location.href='C_TServlet?op=list&cid='"+cid+"';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('指派老师失败！');history.back();</script>");

		}
	}

	private void del(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String tid=request.getParameter("tid");
		String cid=request.getParameter("cid");
		
		boolean b=ctdao.del(Integer.parseInt(tid));
		if(b)
		{
			out.print("<script type='text/javascript'>alert('删除指派老师成功！');location.href='C_TServlet?op=list&cid='"+cid+"';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('删除指派老师失败！');location.href='C_TServlet?op=list&cid='"+cid+"';</script>");
		}
		
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="C_TServlet?op=list";
		String no=request.getParameter("pageNo");
		String cid=request.getParameter("cid");
		int pageNo=no==null?1:Integer.parseInt(no);
		int pageSize=5;
		int recordCountNo=tdao.countNo();
		int recordCount=ctdao.count();
		
		Course c=cdao.queryById(Integer.parseInt(cid));
		List<Teacher> ntlist=tdao.queryForCourseN(Integer.parseInt(cid));
		List<C_T> ctlist =ctdao.queryForPage(pageNo, pageSize);
		request.setAttribute("cid", cid);
		request.setAttribute("c", c);
		request.setAttribute("ctlist", ctlist);
		request.setAttribute("ntlist", ntlist);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("recordCountNo", recordCountNo);
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("url", url);
		request.getRequestDispatcher("addc_t.jsp")
		.forward(request, response);		
	}

}
