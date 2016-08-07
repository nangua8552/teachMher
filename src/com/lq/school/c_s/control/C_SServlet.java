package com.lq.school.c_s.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.school.c_s.dao.dao.C_SDAO;
import com.lq.school.c_s.dao.factory.C_SDAOFactory;
import com.lq.school.c_s.dao.vo.C_S;
import com.lq.school.c_t.dao.dao.C_TDAO;
import com.lq.school.c_t.dao.factory.C_TDAOFactory;
import com.lq.school.c_t.dao.vo.C_T;
import com.lq.school.course.dao.vo.Course;
import com.lq.school.student.dao.dao.StudentDAO;
import com.lq.school.student.dao.factory.StudentDAOFactory;
import com.lq.school.student.dao.vo.Student;

/**
 * Servlet implementation class C_SServlet
 */
@WebServlet("/C_SServlet")
public class C_SServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private C_TDAO ctdao=C_TDAOFactory.getC_TDAO();
	private C_SDAO csdao=C_SDAOFactory.getC_SDAO();
	private StudentDAO sdao=StudentDAOFactory.getStudentDAO();
	
	public C_SServlet() {
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

	private void del(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter out=response.getWriter();
		String cid=request.getParameter("cid");
		String sno=request.getParameter("sno");
		csdao.del(Integer.parseInt(cid));
		request.setAttribute("sno", sno);
		boolean b=csdao.del(Integer.parseInt(cid));
		if(b)
		{
			out.print("<script type='text/javascript'>alert('删除课程成功！');location.href='C_SServlet?op=list&sno='"+sno+"';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('删除课程失败！');location.href='C_SServlet?op=list&sno='"+sno+"';</script>");
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url="C_SServlet?op=list";
		String no=request.getParameter("pageNo");
		String sno=request.getParameter("sno");
		int pageNo=no==null?1:Integer.parseInt(no);
		int pageSize=5;
		int recordCountNo=ctdao.countNo(sno);
		int recordCount=csdao.count(sno);
		
		Student s=sdao.queryBySno(sno);
		
		System.out.println("list sno:"+sno);
		
		List<C_T> sctlist =ctdao.queryForC_Ssctlist(pageNo, pageSize,sno);
		List<C_T> nctlist =ctdao.queryForC_Snctlist(pageNo, pageSize,sno);
		
		request.setAttribute("sno", sno);
		request.setAttribute("s", s);
		request.setAttribute("sctlist", sctlist);
		request.setAttribute("nctlist", nctlist);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("recordCountNo", recordCountNo);
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("url", url);
		request.getRequestDispatcher("selectcourse.jsp")
		.forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out=response.getWriter();
		String sno=request.getParameter("sno");
		String cid=request.getParameter("cid");	
		Student s=new Student();
		s.setSno(sno);
		Course c=new Course();
		c.setId(Integer.parseInt(cid));
		C_S cs=new C_S();
		cs.setC(c);
		cs.setS(s);
		request.setAttribute("sno", sno);
		boolean b=csdao.add(cs);
		if (b) {
			out.print("<script type='text/javascript'>alert('选课成功！');location.href='C_SServlet?op=list&sno='"+sno+"';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('选课失败！');history.back();</script>");

		}
		

	}

}
