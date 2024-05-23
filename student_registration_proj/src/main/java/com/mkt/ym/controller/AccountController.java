package com.mkt.ym.controller;

import java.io.IOException;

import com.mkt.ym.entity.Account;
import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.MessageType;
import com.mkt.ym.entity.type.Role;
import com.mkt.ym.services.AccountService;
import com.mkt.ym.utils.StuRegException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { 
		"/student/login", 
		"/student/logout",
		"/admin/addAccount",
		"/admin/accountList"

}, loadOnStartup = 1)

public class AccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountService accService;
	private MessageType messageType;

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
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		accService = AccountService.getAccountService();

		switch (req.getServletPath()) {
		case "/student/login":
			req.getSession(true).setAttribute("account", login(req));
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
			break;
		case "/admin/addAccount":
			addAccount(req, resp);
			break;
		}

	}

	private void addAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		var year = req.getParameter("year");
		var maj = req.getParameter("major");
		var roll = req.getParameter("roll");
		var rol = req.getParameter("role");
		var username = req.getParameter("username");
		var password = req.getParameter("password");
		var list = accService.search(new Account(username));
		
		var uniYear = null != year ? Integer.parseInt(year) : null;
		var major = null != maj ? Major.valueOf(maj) : null;
		var role = null != rol ? Role.valueOf(rol) : null;

		try {
			if(null == role || role == Role.STUDENT) {
				
			}

			if (null != list) {
				throw new StuRegException("Username is already taken .Plase choose another one !");
			}

			var acc = new Account(username);
			acc.setPassword(password);
			acc.setStudent(null);
			acc.setRole(role == null || role.equals("---") ? Role.STUDENT : Role.valueOf(req.getParameter("role")));
			accService.save(acc);
			resp.sendRedirect("/admin/add-account.jsp");

		} catch (StuRegException e) {
			messageType = MessageType.WARNING;
			messageType.setMessage(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("message", messageType);
		req.getRequestDispatcher("/admin/add-account.jsp").forward(req, resp);
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
