package com.lq.school.course.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.school.course.dao.dao.CourseDAO;
import com.lq.school.course.dao.factory.CourseDAOFactory;
import com.lq.school.course.dao.vo.Course;

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private CourseDAO cdao=CourseDAOFactory.getCourseDAO();
   
    
    public CourseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String op=request.getParameter("op");
		if("add".equals(op))
		{//���
			add(request,response);
		}else if("list".equals(op))
		{//�б�
			list(request,response);
		}else if("del".equals(op))
		{//ɾ��
			del(request,response);
		}else if("update".equals(op))
		{//����
			update(request,response);
		}else if("toUpdate".equals(op))
		{//to����
			toUpdate(request,response);
		}else{
			System.out.println("δ֪������"+op);
		}
		
	}

	/**
	 * to����
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void toUpdate(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");		
		Course c=cdao.queryById(Integer.parseInt(id));
		
		if(c!=null)
		{
			request.setAttribute("c", c);
			request.getRequestDispatcher("updatecourse.jsp")
					.forward(request, response);
		}else{
			out.print("<script type='text/javascript'>alert('�˿γ̲����ڣ�');location='CourseServlet?op=list';</script>");
		}
	}

	/**
	 * ���·���
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String credit=request.getParameter("credit");
		String id=request.getParameter("id");
		
		Course c=new Course();
		c.setCredit(Integer.parseInt(credit));;
		c.setName(name);
		c.setId(Integer.parseInt(id));
		
		boolean b=cdao.update(c);
		if (b) {
			out.print("<script type='text/javascript'>alert('���¿γ̳ɹ���');location='CourseServlet?op=list';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('���¿γ�ʧ�ܣ�');history.back();</script>");

		}		
	}

	/**
	 * ɾ������
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void del(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		boolean b=cdao.del(Integer.parseInt(id));
		if(b)
		{
			out.print("<script type='text/javascript'>alert('ɾ���γ̳ɹ���');location='CourseServlet?op=list';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('ɾ���γ�ʧ�ܣ�');location='CourseServlet?op=list';</script>");
			
		}
	}

	/**
	 * �б���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url="CourseServlet?op=list";
		String no=request.getParameter("pageNo");
		int pageNo=no==null?1:Integer.parseInt(no);
		int pageSize=5;
		int recordCount=cdao.count();
		List<Course> clist =cdao.queryForPage(pageNo, pageSize);
				
		request.setAttribute("clist", clist);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("url", url);
		request.getRequestDispatcher("course.jsp")
								.forward(request, response);
	}

	/**
	 * ��ӷ���
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String credit=request.getParameter("credit");
		
		Course c=new Course();
		c.setCredit(Integer.parseInt(credit));
		c.setName(name);
	
		boolean b=cdao.add(c);
		if (b) {
			out.print("<script type='text/javascript'>alert('��ӿγ̳ɹ���');location='CourseServlet?op=list';</script>");
		}else{
			out.print("<script type='text/javascript'>alert('��ӿγ�ʧ�ܣ�');history.back();</script>");

		}
		
	}
}
