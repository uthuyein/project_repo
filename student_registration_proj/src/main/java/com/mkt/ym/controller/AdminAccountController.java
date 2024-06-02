package com.mkt.ym.controller;

import java.io.IOException;

import com.mkt.ym.entity.Account;
import com.mkt.ym.services.AccountService;
import com.mkt.ym.utils.StuRegException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/student/login",
		"/student/logout",
		"/admin/addAccount",
		"/admin/accountList" }, loadOnStartup = 1)

public class AdminAccountController extends AccountController {

	private static final long serialVersionUID = 1L;
	private AccountService accService = AccountService.getAccountService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		switch (req.getServletPath()) {
		case "/student/logout":
			req.getSession().invalidate();
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
			break;
		case "/admin/addAccount":
			req.getRequestDispatcher("/admin/add-account.jsp").forward(req, resp);
			break;
		case "/admin/accountList":
			req.getRequestDispatcher("/admin/list-account.jsp").forward(req, resp);
			break;
		default:
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		switch (req.getServletPath()) {
		case "/student/login":
			var acc = login(req);
			req.getSession(true).setAttribute("account", acc);
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
			break;
		case "/admin/addAccount":
			addAccount(req, resp);
			break;
		}
	}



	private Account login(HttpServletRequest req) throws ServletException, IOException {
		var user = req.getParameter("username");
		var pass = req.getParameter("password");
		var list = accService.search(new Account(user));

		try {
			if (null == list || list.isEmpty()) {
				throw new StuRegException("Please re-enter your account name !");
			}
			if (!list.get(0).getPassword().equals(pass)) {
				throw new StuRegException("Please re-enter your password !");
			}
			return list.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	
}
