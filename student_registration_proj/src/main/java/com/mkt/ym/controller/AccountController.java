package com.mkt.ym.controller;

import java.io.IOException;
import java.util.List;

import com.mkt.ym.entity.Account;
import com.mkt.ym.services.AccountService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/student/login", loadOnStartup = 1)

public class AccountController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private AccountService service;
	private  Account account;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			var user = req.getParameter("username");
			var pass= req.getParameter("password");
			var acc = new Account(user,pass);
			
			service = AccountService.getAccountService();
			var list = service.search(acc);
			System.out.println("Account list : "+list.get(0).getLoginId()+"\t"+list.get(0).getRole());
			if(null != list) {
				account = list.get(0);
			}
			
			req.getSession(true).setAttribute("account", account);
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
			
	}

}
