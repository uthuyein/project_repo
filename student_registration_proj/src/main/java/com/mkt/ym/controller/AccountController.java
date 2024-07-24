package com.mkt.ym.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.mkt.ym.entity.Account;
import com.mkt.ym.entity.Messenger;
import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.UniversityInfo;
import com.mkt.ym.entity.UniversityInfoPK;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.entity.type.MessageType;
import com.mkt.ym.entity.type.Role;
import com.mkt.ym.services.AccountService;
import com.mkt.ym.services.MessengerService;
import com.mkt.ym.services.UniversityInfoService;
import com.mkt.ym.utils.StuRegException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static com.mkt.ym.utils.NrcConverter.getNrc;

@WebServlet(urlPatterns = { "/admin/addAccount", "/admin/accountList", "/admin/updateAccount", "/admin/deleteAccount",
		"/student/addAccount", "/student/signUp", "/student/login", "/student/logout" }, loadOnStartup = 1)
public class AccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountService accService = AccountService.getAccountService();
	private UniversityInfoService uniService = UniversityInfoService.getUniversityInfoService();
	private MessengerService mSerivice = MessengerService.getMessengerService();
	private MessageType message;

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

		case "/admin/updateAccount":
			var id = Integer.valueOf(req.getParameter("id"));
			var updAcc = accService.search(new Account(id)).get(0);
			req.setAttribute("updAcc", updAcc);
			req.getRequestDispatcher("/admin/addAccount.jsp").forward(req, resp);
			break;

		case "/admin/deleteAccount":
			id = Integer.valueOf(req.getParameter("id"));
			var acc = new Account();
			acc.setId(id);
			accService.delete(acc);
			acc.setId(null);
			acc.setActive(true);
			var listAccount = accService.search(null);
			req.setAttribute("listAccount", listAccount);
			req.getRequestDispatcher("/admin/listAccount.jsp").forward(req, resp);
			break;

		case "/student/addAccount":
			req.getRequestDispatcher("/student/addAccount.jsp").forward(req, resp);
			break;

		case "/student/signUp":
			req.getRequestDispatcher("/student/signUp.jsp").forward(req, resp);
			break;

		case "/admin/accountList":
			acc = new Account();
			acc.setActive(true);
			listAccount = accService.search(acc);
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
			login(req, resp);
			break;

		case "/admin/addAccount":
		case "/student/addAccount":
			addAccount(req, resp);
			break;

		case "/admin/updateAccount":
			updateAccount(req, resp);
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
		var user = req.getParameter("userName");
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
		var account = new Account(loginId);
		account.setActive(true);
		var list = accService.search(account);
		String link = null;
		var session = req.getSession(true);

		try {
			if (null == list || list.isEmpty()) {
				throw new StuRegException("Please re-enter your loginId !");
			}
			var acc = list.get(0);
			if (!acc.getPassword().equals(pass)) {
				throw new StuRegException("Please re-enter your password !");
			}
			if (acc.getRole() == Role.ADMIN) {
				link = "/index.jsp";
			} else {
				link = "/student/detailStudent.jsp";
			}
			if (acc.getRole() == Role.STUDENT) {
				var uniInfo = acc.getUniInfo();
				var dto = new UniversityInfoDto(uniInfo.getId().getOpenYear(), uniInfo.getId().getUniYear(),
						uniInfo.getId().getMajor(), uniInfo.getStudent().getName());
				var uniInfoDto = uniService.searchUniversityInfo(dto).get(0);
				var messengers = mSerivice.search(new Messenger(new Student(uniInfoDto.stuId())));
				req.setAttribute("messengers", messengers);
				session.setAttribute("uniInfoDto", uniInfoDto);
			}
			session.setAttribute("account", list.get(0));

		} catch (Exception e) {
			message = MessageType.ERROR;
			message.setMessage(e.getMessage());
			req.setAttribute("message", message);
		}
		
		req.getRequestDispatcher(null != link ? link : "/index.jsp").forward(req, resp);
	}

	private void updateAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		var id = req.getParameter("id");
		Integer accId = (null != id) ? Integer.valueOf(id) : null;

		try {
			var updAcc = accService.search(new Account(accId)).get(0);			
			var acc = getAccount(req);
			
			updAcc.setActive(true);
			updAcc.setLoginId(acc.getLoginId());
			updAcc.setUsername(acc.getUsername());
			updAcc.setPassword(acc.getPassword());
					
			if (updAcc.getRole() == Role.STUDENT) {
				MessageType m = null;
				m = MessageType.WARNING;
				m.setMessage("""
							Dear %s , your account information was updated  with user name of %s and password is  %s
						""".formatted(updAcc.getUsername(), updAcc.getLoginId(), updAcc.getPassword()));

				var messenger = new Messenger();
				messenger.setMessage(m);
				messenger.setText(m.getMessage());
				messenger.setStudent(updAcc.getUniInfo().getStudent());
				updAcc.addMessage(messenger);
			}
			
			accService.update(updAcc);	
			message = MessageType.SUCCESS;
			message.setMessage("Successfully update account!");

		} catch (Exception e) {
			e.printStackTrace();
			message = MessageType.ERROR;
			message.setMessage(e.getMessage());
		}
		req.setAttribute("message", message);
		req.getRequestDispatcher("/admin/addAccount.jsp").forward(req, resp);
	}

	private void addAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		var acc = getAccount(req);
		try {
			var list = accService.search(acc);
			if (null != list && list.size() > 0) {
				throw new StuRegException("Aleardy create account for this username !");
			}
			
			if (acc.getRole() == Role.STUDENT) {
				UniversityInfoDto uniInfoDto = (UniversityInfoDto) req.getSession(true).getAttribute("uniInfoDto");
				acc.setUniInfo(getUniInfo(uniInfoDto));
			}
			
			accService.save(acc);
			message = MessageType.SUCCESS;
			message.setMessage("Successfully create account!");

		} catch (StuRegException e) {
			message = MessageType.ERROR;
			message.setMessage(e.getMessage());
		}
		req.setAttribute("message", message);
		Account sessionAcc = (Account) req.getSession().getAttribute("account");
		req.getRequestDispatcher(((null != sessionAcc && sessionAcc.getRole() == Role.ADMIN) ? "/admin" : "/student")
				.concat("/addAccount.jsp")).forward(req, resp);

	}
	
	private Account getAccount(HttpServletRequest req) throws IOException, ServletException {
		var username = req.getParameter("username");
		var rol = req.getParameter("role");
		var loginId = req.getParameter("loginId");
		var password = req.getParameter("password");
		var confirm = req.getParameter("confirm");
		Role role = (null != rol) ? Role.valueOf(rol) : Role.STUDENT;

		var acc = new Account(username, loginId);
		acc.setRole(role);
		acc.setUsername(username.toLowerCase());
		acc.setLoginId(loginId.toLowerCase());

		if (!password.equals(confirm)) {
			throw new StuRegException("Password did not match confirm password and try again !");
		}
		acc.setPassword(password);
		return acc;
	}

	private UniversityInfo getUniInfo(UniversityInfoDto dto) {
		var uniPk = new UniversityInfoPK();
		uniPk.setMajor(dto.major());
		uniPk.setOpenYear(dto.openYear());
		uniPk.setUniYear(dto.uniYear());
		uniPk.setRollNumber(dto.rollNumber());
		var uniInfo = new UniversityInfo(uniPk, new Student(dto.stuId()));
		return uniInfo;
	}

	private void signUpStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var mService = MessengerService.getMessengerService();
		try {
			var stuName = req.getParameter("stuName");
			var dob = LocalDate.parse(req.getParameter("dob"));
			var nrc = getNrc("", req);
			var fNrc = getNrc("f", req);
			var mNrc = getNrc("m", req);
			var schEnroll = req.getParameter("schEnroll");
			var schMarks = Integer.parseInt(req.getParameter("schMarks"));
			var dto = new UniversityInfoDto(null, null, null, stuName, dob, nrc, fNrc, mNrc, schEnroll, schMarks);

			UniversityInfoDto uniInfoDto = uniService.searchUniversityInfo(dto).stream().findFirst().orElse(null);
			if (null == uniInfoDto) {
				throw new StuRegException("There is no student for that information ! ");
			}
			var session = req.getSession(true);
			var messenger = new Messenger(new Student(uniInfoDto.stuId()));
			session.setAttribute("messengers", mService.search(messenger));
			session.setAttribute("uniInfoDto", uniInfoDto);
			req.getRequestDispatcher("/student/detailStudent.jsp").forward(req, resp);

		} catch (Exception e) {
			message = MessageType.ERROR;
			message.setMessage(e.getMessage());
			req.setAttribute("message", message);
			req.getRequestDispatcher("/student/signUp.jsp").forward(req, resp);
		}

	}

}
