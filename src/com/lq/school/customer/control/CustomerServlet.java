package com.lq.school.customer.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lq.school.customer.dao.dao.CustomerDAO;
import com.lq.school.customer.dao.factory.CustomerDAOFactory;
import com.lq.school.customer.dao.vo.Customer;
import com.lq.school.util.MD5Util;

public class CustomerServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//��α���-->�̰߳�ȫ
	private CustomerDAO cdao=CustomerDAOFactory.getCustomerDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String op=req.getParameter("op");
		if("login".equals(op))
		{//��¼
			login(req,resp);
		}else{
			System.out.println("δ֪������"+op);
		}
	}
	/**
	 * ��¼����
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String account=req.getParameter("account");
		String password=req.getParameter("password");
		String safecode=req.getParameter("safecode");
		String safe=(String) req.getSession().getAttribute("safecode");
		PrintWriter out=resp.getWriter();
		if(safe.equals(safecode))
		{//��֤�˺�����
			Customer c=cdao.queryByAccount(account);
			
			if(c!=null&&c.getPassword().equals(MD5Util.MD5(password)))
			{//�ɹ�
				req.getSession().setAttribute("user", c);
				resp.sendRedirect("main.html");
			}else{//ʧ��
				out.println("<script type='text/javascript'>alert('�˺Ż�������󣡣�');history.back();</script>");
			}
		}else{
			out.println("<script type='text/javascript'>alert('��֤����󣡣�');history.back();</script>");
		}
	}

}
