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
	//如何变量-->线程安全
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
		{//登录
			login(req,resp);
		}else{
			System.out.println("未知操作："+op);
		}
	}
	/**
	 * 登录方法
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
		{//验证账号密码
			Customer c=cdao.queryByAccount(account);
			
			if(c!=null&&c.getPassword().equals(MD5Util.MD5(password)))
			{//成功
				req.getSession().setAttribute("user", c);
				resp.sendRedirect("main.html");
			}else{//失败
				out.println("<script type='text/javascript'>alert('账号或密码错误！！');history.back();</script>");
			}
		}else{
			out.println("<script type='text/javascript'>alert('验证码错误！！');history.back();</script>");
		}
	}

}
