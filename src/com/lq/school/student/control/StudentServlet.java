package com.lq.school.student.control;

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
import com.lq.school.student.dao.dao.StudentDAO;
import com.lq.school.student.dao.factory.StudentDAOFactory;
import com.lq.school.student.dao.vo.Student;
import com.lq.school.util.PageBean;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StudentDAO sdao=StudentDAOFactory.getStudentDAO();
	private ClasseDAO cdao=ClasseDAOFactory.getClasseDAO();
	
    public StudentServlet() {
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
		}else if("toAdd".equals(op))
		{//to添加
			toAdd(request,response);
		}else if("list".equals(op))
		{//列表
			list(request,response);
		}else if("del".equals(op))
		{//删除
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
		String sno=request.getParameter("sno");
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String sex=request.getParameter("sex");
		String tel=request.getParameter("tel");
		String address=request.getParameter("address");
		String cid=request.getParameter("cid");
		Classe c=new Classe();
		c.setId(Integer.parseInt(cid));
		Student s=new Student(sno, name, Integer.parseInt(age), sex, tel, address, c);
		boolean b=sdao.update(s);
		if (b) {
			out.print("<script type='text/javascript'>alert('更新学生成功！');location='StudentServlet?op=list';</script>");
			//response.sendRedirect("TeacherServlet?op=tlist");
		}else{
			out.print("<script type='text/javascript'>alert('更新学生失败！');history.back();</script>");

		}		
	}

	private void toUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		List<Classe> clist=cdao.queryForStudent();
		
		String sno=request.getParameter("sno");
		Student s=sdao.queryBySno(sno);
		if(s!=null)
		{
			request.setAttribute("s", s);
			
			request.setAttribute("clist", clist);
			
			request.getRequestDispatcher("updatestudent.jsp")
					.forward(request, response);
		}else{
			out.print("<script type='text/javascript'>alert('此学生不存在！');location='StudentServlet?op=list';</script>");
		}
	}

	private void del(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter out=response.getWriter();
		String sno=request.getParameter("sno");
		boolean b=sdao.del(sno);
		if(b)
		{
			out.print("<script type='text/javascript'>alert('删除学生成功！');location='StudentServlet?op=list';</script>");
			//resp.sendRedirect("TeacherServlet?op=tlist");
		}else{
			out.print("<script type='text/javascript'>alert('删除学生失败！');location='StudentServlet?op=list';</script>");
			//resp.sendRedirect("TeacherServlet?op=tlist");
			
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageBean<Student> page=new PageBean<Student>();
		String pageNo=request.getParameter("pageNo");
		if(pageNo==null)
		{
			pageNo="1";
		}
		int recordCount=sdao.count();
		String url="StudentServlet?op=list";
		List<Student> clist=sdao.queryForPage(Integer.parseInt(pageNo), page.getPageSize());
		page.setRecordCount(recordCount);
		page.setList(clist);
		page.setPageNo(Integer.parseInt(pageNo));
		page.setUrl(url);
		request.setAttribute("page", page);
		request.getRequestDispatcher("student.jsp")
				.forward(request, response);		
	}

	private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Classe> clist=cdao.queryForStudent();		
		request.setAttribute("clist", clist);
		request.getRequestDispatcher("addstudent.jsp")
				.forward(request, response);
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out =response.getWriter();
		String sno=request.getParameter("sno");
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String sex=request.getParameter("sex");
		String tel=request.getParameter("tel");
		String address=request.getParameter("address");
		String cid=request.getParameter("cid");
		Classe c=new Classe();
		c.setId(Integer.parseInt(cid));
		Student s=new Student(sno, name, Integer.parseInt(age), sex, tel, address, c);
		boolean b=sdao.add(s);
		if (b) {
			out.print("<script type='text/javascript'>alert('添加学生成功！');location='StudentServlet?op=list';</script>");
			//response.sendRedirect("TeacherServlet?op=tlist");
		}else{
			out.print("<script type='text/javascript'>alert('添加学生失败！');history.back();</script>");

		}
	}

}
