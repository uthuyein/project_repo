package com.mkt.ym.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.mkt.ym.entity.Account;
import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.type.Role;
import com.mkt.ym.services.AccountService;
import com.mkt.ym.utils.StuRegException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountService accService;

	void addAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		accService = AccountService.getAccountService();

		var year = req.getParameter("uniYear");
		var maj = req.getParameter("major");
		var rol = req.getParameter("role");

		var stuName = req.getParameter("stuName");
		var stuNrc = req.getParameter("nrc");
		var username = req.getParameter("username");
		var password = req.getParameter("password");
		var confirm = req.getParameter("confirm");

		var acc = new Account(username);
		var list = accService.search(acc);

		var role = null != rol && !rol.equals("---") ? Role.valueOf(rol) : Role.STUDENT;

		try {
			if (null != list && list.size() > 0) {
				System.out.println("Name :" + list.get(0).getStudent().getName());
			}
			if (role == Role.STUDENT) {
				acc.setStudent(getStudent(req, stuName, stuNrc).get());
			}
			if (!password.equals(confirm)) {
				throw new StuRegException("Password did not match confirm password and try again !");
			}

			acc.setPassword(password);
			acc.setRole(role);
			accService.save(acc);
			resp.sendRedirect("/admin/add-account.jsp");

		} catch (StuRegException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/admin/add-account.jsp").forward(req, resp);
	}

	@SuppressWarnings("unchecked")
	private Optional<Student> getStudent(HttpServletRequest req, String name, String nrc) {
		return ((List<Student>) req.getSession().getAttribute("listStudent")).stream()
				.filter(s -> s.getName().equalsIgnoreCase(name) && s.getNrc().equalsIgnoreCase(nrc)).findFirst();
	}

}
