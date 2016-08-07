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
	//��α���-->�̰߳�ȫ
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
		{//���
			add(req,resp);
		}else if("tlist".equals(op))
		{//�б�
			list(req,resp);
		}else if("del".equals(op))
		{//ɾ��
			del(req,resp);
		}else if("toUpdate".equals(op))
		{//to����
			toUpdate(req,resp);
		}else if("update".equals(op))
		{//����
			update(req,resp);
		}else{
			System.out.println("δ֪������"+op);
		}
	}
	
	/**
	 * ���·���
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
			out.print("<script type='text/javascript'>alert('������ʦ�ɹ���');location='TeacherServlet?op=tlist';</script>");
			//response.sendRedirect("TeacherServlet?op=tlist");
		}else{
			out.print("<script type='text/javascript'>alert('������ʦʧ�ܣ�');history.back();</script>");

		}
	}

	/**
	 * to����
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
			out.print("<script type='text/javascript'>alert('����ʦ�����ڣ�');location='TeacherServlet?op=tlist';</script>");
		}
	}

	/**
	 * ɾ������
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
			out.print("<script type='text/javascript'>alert('ɾ����ʦ�ɹ���');location='TeacherServlet?op=tlist';</script>");
			//resp.sendRedirect("TeacherServlet?op=tlist");
		}else{
			out.print("<script type='text/javascript'>alert('ɾ����ʦʧ�ܣ�');location='TeacherServlet?op=tlist';</script>");
			//resp.sendRedirect("TeacherServlet?op=tlist");
			
		}
	}

	/**
	 * �б���
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
	 * ��ӷ���
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
			out.print("<script type='text/javascript'>alert('�����ʦ�ɹ���');location='TeacherServlet?op=tlist';</script>");
			//response.sendRedirect("TeacherServlet?op=tlist");
		}else{
			out.print("<script type='text/javascript'>alert('�����ʦʧ�ܣ�');history.back();</script>");

		}
	}

}
