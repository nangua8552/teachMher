package com.lq.school.classe.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.school.classe.dao.dao.ClasseDAO;
import com.lq.school.classe.dao.factory.ClasseDAOFactory;
import com.lq.school.classe.dao.vo.Classe;
import com.lq.school.teacher.dao.dao.TeacherDAO;
import com.lq.school.teacher.dao.factory.TeacherDAOFactory;
import com.lq.school.teacher.dao.vo.Teacher;
import com.lq.school.util.PageBean;

/**
 * Servlet implementation class ClasseServlet
 */


public class ClasseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private ClasseDAO cdao=ClasseDAOFactory.getClasseDAO();
	private TeacherDAO tdao=TeacherDAOFactory.getTeacherDAO();

    public ClasseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String op=request.getParameter("op");
		if("add".equals(op))
		{//添加
			add(request,response);
		}else if("toAdd".equals(op))
		{//to添加
			toAdd(request,response);
		}else if("list".equals(op))
		{//列表
			list(request,response);
		}else if("del".equals(op))
		{
			del(request,response);
		}else if("toUpdate".equals(op))
		{//to更新
			toUpdate(request,response);
		}else if("update".equals(op))
		{//更新
			update(request,response);
		}else{
			System.out.println("未知操作："+op);
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String college=request.getParameter("college");
		String id=request.getParameter("id");
		String tid=request.getParameter("tid");
		Classe c=new Classe();
		c.setCollege(college);
		c.setId(Integer.parseInt(id));
		c.setName(name);
		Teacher t=new Teacher();
		t.setId(Integer.parseInt(tid));
		c.setT(t);
		boolean b=cdao.update(c);
		if (b) {
			out.print("<script type='text/javascript'>alert('更新班级成功！');location='ClasseServlet?op=list';</script>");
			//response.sendRedirect("TeacherServlet?op=tlist");
		}else{
			out.print("<script type='text/javascript'>alert('更新班级失败！');history.back();</script>");

		}		
	}

	private void toUpdate(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out=response.getWriter();
		List<Teacher> tlist=tdao.queryForClasse();
		
		String id=request.getParameter("id");
		Classe c=cdao.queryById(Integer.parseInt(id));
		if(c!=null)
		{
			request.setAttribute("c", c);
			
			request.setAttribute("tlist", tlist);
			
			request.getRequestDispatcher("updateclasse.jsp")
					.forward(request, response);
		}else{
			out.print("<script type='text/javascript'>alert('此班级不存在！');location='ClasseServlet?op=list';</script>");
		}
	}

	private void del(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		boolean b=cdao.del(Integer.parseInt(id));
		if(b)
		{
			out.print("<script type='text/javascript'>alert('删除班级成功！');location='ClasseServlet?op=list';</script>");
			//resp.sendRedirect("TeacherServlet?op=tlist");
		}else{
			out.print("<script type='text/javascript'>alert('删除班级失败！');location='ClasseServlet?op=list';</script>");
			//resp.sendRedirect("TeacherServlet?op=tlist");
			
		}
	}

	private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Teacher> tlist=tdao.queryForClasse();
		
		request.setAttribute("tlist", tlist);
		request.getRequestDispatcher("addclasse.jsp")
				.forward(request, response);
		
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PageBean<Classe> page=new PageBean<Classe>();
		String pageNo=request.getParameter("pageNo");
		if(pageNo==null)
		{
			pageNo="1";
		}
		int recordCount=cdao.count();
		String url="ClasseServlet?op=list";
		List<Classe> clist=cdao.queryForPage(Integer.parseInt(pageNo), page.getPageSize());
		page.setRecordCount(recordCount);
		page.setList(clist);
		page.setPageNo(Integer.parseInt(pageNo));
		page.setUrl(url);
		request.setAttribute("page", page);
		request.getRequestDispatcher("classe.jsp")
				.forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out =response.getWriter();
		String name=request.getParameter("name");
		String college=request.getParameter("college");
		String tid=request.getParameter("tid");
		Teacher t=new Teacher();
		t.setId(Integer.parseInt(tid));
		Classe c=new Classe(0,name,college,t);
		boolean b=cdao.add(c);
		if (b) {
			out.print("<script type='text/javascript'>alert('添加班级成功！');location='ClasseServlet?op=list';</script>");
			//response.sendRedirect("TeacherServlet?op=tlist");
		}else{
			out.print("<script type='text/javascript'>alert('添加班级失败！');history.back();</script>");

		}
		
	}

}
