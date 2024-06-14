package com.mkt.ym.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.mkt.ym.entity.Account;
import com.mkt.ym.entity.Payment;
import com.mkt.ym.entity.PaymentPk;
import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.UniversityInfo;
import com.mkt.ym.entity.UniversityInfoPK;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.entity.type.Message;
import com.mkt.ym.entity.type.PaymentType;
import com.mkt.ym.entity.type.Role;
import com.mkt.ym.entity.type.UniYear;
import com.mkt.ym.services.AccountService;
import com.mkt.ym.services.PaymentService;
import com.mkt.ym.services.UniversityInfoService;
import com.mkt.ym.utils.StuRegException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/student/chkStudent", "/student/register", "/student/payment", "/student/account" })
public class StudentAccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UniversityInfoService uniService;
	private PaymentService payService;
	private UniversityInfo uniInfo;
	private AccountService accService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		uniService = UniversityInfoService.getUniversityInfoService();
		payService = PaymentService.getPaymentService();
		accService = AccountService.getAccountService();

		String name = "";
		switch (req.getServletPath()) {
		case "/student/register":
			name = "register";
			break;

		case "/student/payment":
			name = "payment";
			break;

		case "/student/account":
			name = "account";
			break;
		}
		req.getRequestDispatcher("/student/" + name + ".jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		switch (req.getServletPath()) {
		case "/student/register":
			getUniInfoDto(req, resp);
			break;

		case "/student/payment":
			getPayment(req, resp);
			break;

		case "/student/account":
			addAccount(req, resp);
			break;
		}
	}

	private void getUniInfoDto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			var stuName = req.getParameter("stuName");
			var dob = LocalDate.parse(req.getParameter("dob"));
			var nrc = req.getParameter("nrc");
			var fNrc = req.getParameter("fNrc");
			var mNrc = req.getParameter("mNrc");
			var schEnroll = req.getParameter("schEnroll");
			var schMarks = Integer.parseInt(req.getParameter("schMarks"));

			var oYear = req.getParameter("openYear");

			if (null != oYear && !oYear.matches("[0-9]+$")) {
				throw new StuRegException("University open year must be digit");
			}
			var openYear = Integer.parseInt(oYear);

			var uniYear = UniYear.valueOf(null != req.getParameter("uniYear") ? req.getParameter("uniYear") : "FIRST");
			var dto = new UniversityInfoDto(openYear, uniYear, null, stuName, dob, nrc, fNrc, mNrc, schEnroll,
					schMarks);

			var uniInfoDto = uniService.searchUniversityInfo(dto).stream().findFirst().orElse(null);
			if (null == uniInfoDto) {
				throw new StuRegException("There is no student for that information ! ");
			}
			
			var uniInfoPk = new UniversityInfoPK(uniInfoDto.openYear(), uniInfoDto.rollNumber(), uniInfoDto.major(),
					uniInfoDto.uniYear());
			var student = new Student();
			student.setId(uniInfoDto.stuId());
			uniInfo = new UniversityInfo(uniInfoPk, student);
			req.getRequestDispatcher("/student/payment.jsp").forward(req, resp);

		} catch (Exception e) {
			createMessage(e, req, resp);
		}

	}

	private void getPayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			var pay = req.getParameter("payment");
			var tName = req.getParameter("tName");
			var tNum = req.getParameter("tNum");
			var amo = req.getParameter("amount");
			var note = req.getParameter("note");

			if (!amo.matches("[0-9]+")) {
				throw new StuRegException("Amount must be digit only !");
			}

			var payType = (null != pay) ? PaymentType.valueOf(pay) : null;
			var amount = (null != amo) ? Integer.valueOf(amo) : 0;

			var pk = new PaymentPk(payType, LocalDate.now(), LocalTime.now());
			var payment = new Payment();
			payment.setId(pk);
			payment.setTransferFrom(tName);
			payment.setTransactionNum(tNum);
			payment.setAmount(amount);
			payment.setNote(note);
			payment.setUniInfo(uniInfo);
			payService.save(payment);
			req.getRequestDispatcher("/student/account.jsp").forward(req, resp);

		} catch (Exception e) {
			createMessage(e, req, resp);
		}
	}

	private void addAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		var username = req.getParameter("username");
		var password = req.getParameter("password");
		var confirm = req.getParameter("confirm");
		var active = req.getParameter("active");
		var acc = new Account(username);

		try {

			var list = accService.search(acc);
			if (null != list && list.size() > 0) {
				throw new StuRegException("Aleardy create account for this username !");
			}

			if (!password.equals(confirm)) {
				throw new StuRegException("Password did not match confirm password and try again !");
			}

			acc.setStudent(uniInfo.getStudent());
			acc.setLoginId(username.toLowerCase());
			acc.setPassword(password);
			acc.setRole(Role.STUDENT);
			acc.setActive((null != active && active.equals("on")) ? true : false);
			accService.save(acc);
			resp.sendRedirect("/index.jsp");
			
		} catch (StuRegException e) {
			System.out.println("Account controller messgage :::::" + e.getMessage());
			e.printStackTrace();
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

}
