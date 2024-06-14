package com.mkt.ym.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.mkt.ym.entity.Account;
import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.UniversityInfo;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.Message;
import com.mkt.ym.entity.type.Role;
import com.mkt.ym.entity.type.UniYear;
import com.mkt.ym.services.AccountService;
import com.mkt.ym.utils.StuRegException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/student/login", "/student/logout", "/admin/addAccount",
		"/admin/accountList" }, loadOnStartup = 1)

public class AdminAccountController extends HttpServlet {

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
			req.getRequestDispatcher("/admin/addAccount.jsp").forward(req, resp);
			break;

		case "/admin/accountList":
			req.getRequestDispatcher("/admin/listAccount.jsp").forward(req, resp);
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
			addAccount(req, resp, null);
			break;
		}
	}

	private Account login(HttpServletRequest req) throws ServletException, IOException {
		var user = req.getParameter("username").toLowerCase();
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

	void addAccount(HttpServletRequest req, HttpServletResponse resp, UniversityInfo uniInfo)
			throws IOException, ServletException {
		accService = AccountService.getAccountService();
	
		var year = req.getParameter("uniYear");
		var maj = req.getParameter("major");
		var rol = req.getParameter("role");
		var rollNum = req.getParameter("uniRollNum");
		var stuName = req.getParameter("stuName");
		var username = req.getParameter("username");
		var password = req.getParameter("password");
		var active = req.getParameter("active");
		var acc = new Account(username);
		var student = new Student();

		try {

			var list = accService.search(acc);
			if (null != list && list.size() > 0) {
				throw new StuRegException("Aleardy create account for this username !");
			}

			var role = (null != rol) ? Role.valueOf(rol) : Role.STUDENT;
			var uniYear = (null != year) ? UniYear.valueOf(year) : UniYear.FIRST;
			var major = (null != maj) ? Major.valueOf(maj) : null;

			var studentDto = getStudent(req, stuName, rollNum,uniYear,major).orElse(null);
			
			if (null == studentDto) {
				throw new StuRegException("There is no student for that name !");
			}
			student.setId(studentDto.stuId());
			acc.setStudent(student);

			acc.setLoginId(username.toLowerCase());
			acc.setPassword(password);
			acc.setRole(role);
			acc.setActive(active.equals("on") ? true : false);
			accService.save(acc);
			resp.sendRedirect("/admin/addAccount.jsp");

		} catch (StuRegException e) {
			createMessage(e, req, resp);
		}

	}

	void createMessage(Exception e, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Message message = Message.ERROR;
		message.setMessage(e.getMessage());
		req.setAttribute("message", message);
		req.getRequestDispatcher(req.getServletPath() + ".jsp").forward(req, resp);
	}

	@SuppressWarnings("unchecked")
	private Optional<UniversityInfoDto> getStudent(HttpServletRequest req, String name, String rollNum, UniYear uniYear,Major major) {
		List<UniversityInfoDto> list = (List<UniversityInfoDto>) req.getAttribute("listUniInfo");
		list.forEach(l -> System.out.println(l.name()+"\t"+l.rollNumber()+"\t"+l.uniYear()+"\t"+l.major()));
		return list.stream()
				.filter(s -> 
				s.name().equalsIgnoreCase(name)
				&& s.rollNumber() .equalsIgnoreCase(rollNum) 
				&& s.major() == major)
				.findAny();
	}

}
