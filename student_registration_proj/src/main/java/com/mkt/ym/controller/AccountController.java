package com.mkt.ym.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.mkt.ym.entity.Account;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.entity.type.Message;
import com.mkt.ym.entity.type.Role;
import com.mkt.ym.services.AccountService;
import com.mkt.ym.services.UniversityInfoService;
import com.mkt.ym.utils.StuRegException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/student/login", "/student/logout", "/admin/addAccount", "/admin/accountList",
		"/student/addAccount" ,"/student/signUp",}, loadOnStartup = 1)

public class AccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private AccountService accService =  AccountService.getAccountService();
	private UniversityInfoService uniService;
	private Message message;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		uniService = UniversityInfoService.getUniversityInfoService();
		
		switch (req.getServletPath()) {
		case "/student/logout":
			req.getSession().invalidate();
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
			break;

		case "/admin/addAccount":
			req.getRequestDispatcher("/admin/addAccount.jsp").forward(req, resp);
			break;
		case"/student/addAccount":
			req.getRequestDispatcher("/student/addAccount.jsp").forward(req, resp);
			break;
		case "/student/signUp":
			req.getRequestDispatcher("/student/signUp.jsp").forward(req, resp);
			break;

		case "/admin/accountList":
			var listAccount = accService.search(null);
			req.setAttribute("listAccount", listAccount);
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
			login(req,resp);		
			break;

		case "/admin/addAccount":
			addAccount(req, resp,Role.ADMIN);
			break;
		case "/student/addAccount":
			addAccount(req, resp,Role.STUDENT);
			break;
		case "/student/signUp":
			signUpStudent(req, resp);
			break;

		case "/admin/accountList":
			var listAccount = searchAccount(req);
			req.setAttribute("listAccount", listAccount);
			req.getRequestDispatcher("/admin/listAccount.jsp").forward(req, resp);
			break;

		}
	}

	private List<Account> searchAccount(HttpServletRequest req) {
		var user = req.getParameter("username");
		var login = req.getParameter("loginId");
		var r = req.getParameter("role");
		
		var role = null != r ? Role.valueOf(r) : null;
		var acc = new Account(user, login);
		acc.setRole(role);
		return accService.search(acc);
		
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		accService = AccountService.getAccountService();

		var loginId = req.getParameter("loginId").toLowerCase();
		var pass = req.getParameter("password");
		var list = accService.search(new Account(loginId));
		String link = null;
		try {
			if (null == list || list.isEmpty()) {
				throw new StuRegException("Please re-enter your loginId !");
			}
			var acc = list.get(0);
			if (!acc.getPassword().equals(pass)) {
				throw new StuRegException("Please re-enter your password !");
			}
			
			if(acc.getRole() == Role.ADMIN) {
				link = "/index.jsp";
			}else {
				link = "/student/detailStudent.jsp";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getSession(true).setAttribute("account",list.get(0));
		req.getRequestDispatcher(link).forward(req, resp);

	}

	private void addAccount(HttpServletRequest req, HttpServletResponse resp,Role role) throws IOException, ServletException {
		var username = req.getParameter("username");
		var loginId = req.getParameter("loginId");
		var password = req.getParameter("password");
		var confirm = req.getParameter("confirm");
		var acc = new Account(username,loginId);
		
	
		try {

			var list = accService.search(acc);
			if (null != list && list.size() > 0) {
				throw new StuRegException("Aleardy create account for this username !");
			}
			
			if (!password.equals(confirm)) {
				throw new StuRegException("Password did not match confirm password and try again !");
			}

			acc.setUsername(username.toLowerCase());
			acc.setLoginId(loginId.toLowerCase());
			acc.setPassword(password);
			acc.setRole(role);
			accService.save(acc);
			message = Message.SUCCESS;
			message.setMessage("Successfully create account!");
			
		} catch (StuRegException e) {
			message = Message.ERROR;
			message.setMessage(e.getMessage());
		}
		req.setAttribute("message", message);
		req.getRequestDispatcher(((role == Role.ADMIN ) ? "/admin" : "/student").concat("/addAccount.jsp")).forward(req, resp);

	}

	private void signUpStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		try {
			var stuName = req.getParameter("stuName");
			var dob = LocalDate.parse(req.getParameter("dob"));
			var nrc = req.getParameter("nrc");
			var fNrc = req.getParameter("fNrc");
			var mNrc = req.getParameter("mNrc");
			var schEnroll = req.getParameter("schEnroll");
			var schMarks = Integer.parseInt(req.getParameter("schMarks"));
			var dto = new UniversityInfoDto(null, null, null, stuName, dob, nrc, fNrc, mNrc, schEnroll, schMarks);

			UniversityInfoDto uniInfoDto = uniService.searchUniversityInfo(dto).stream().findFirst().orElse(null);
			if (null == uniInfoDto) {
				throw new StuRegException("There is no student for that information ! ");
			}

			var session = req.getSession();
			session.setAttribute("uniInfoDto", uniInfoDto);
			req.getRequestDispatcher("/student/detailStudent.jsp").forward(req, resp);

		} catch (Exception e) {
			message = Message.ERROR;
			message.setMessage(e.getMessage());
			req.setAttribute("message", message);
			req.getRequestDispatcher("/student/signUp.jsp").forward(req, resp);
		}

	}

}
