package com.mkt.ym.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.mkt.ym.entity.Payment;
import com.mkt.ym.entity.RegisterPk;
import com.mkt.ym.entity.UniversityInfo;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.PaymentType;
import com.mkt.ym.entity.type.UniYear;
import com.mkt.ym.services.PaymentService;
import com.mkt.ym.services.UniversityInfoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/student/chkStudent", "/student/register" })
public class RegistrationController extends AccountController {

	private static final long serialVersionUID = 1L;
	private UniversityInfoService uniService;
	private PaymentService pService;
	private UniversityInfo uniInfo;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		uniService = UniversityInfoService.getUniversityInfoService();
		pService = PaymentService.getPaymentService();		
		req.getRequestDispatcher("/student/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var dto = getUniInfoDto(req);
		if (null != dto) {
			
			createPayment(req);
			addAccount(req, resp);
			
		}else {
			System.out.println("************************");
		}
		req.getRequestDispatcher("/student/register.jsp").forward(req, resp);
	}

	private UniversityInfoDto getUniInfoDto(HttpServletRequest req) {

		var stuName = req.getParameter("stuName");
		var dob = LocalDate.parse(req.getParameter("dob"));
		var nrc = req.getParameter("nrc");
		var fNrc = req.getParameter("fNrc");
		var mNrc = req.getParameter("mNrc");
		var schEnroll = req.getParameter("schEnroll");
		var schMarks = Integer.parseInt(req.getParameter("schMarks"));
		var openYear = Integer.parseInt(req.getParameter("openYear"));

		var uniYear = UniYear.valueOf(null != req.getParameter("uniYear")?req.getParameter("uniYear"):"FIRST");
		var major = Major.valueOf(req.getParameter("major"));
		
		var dto = new UniversityInfoDto(openYear,uniYear,major, stuName, dob, nrc, fNrc, mNrc, schEnroll, schMarks);

		var uniInfoDto = uniService.searchUniversityInfo(dto).stream().findFirst().orElse(null);
		return uniInfoDto;
	}

	private void createPayment(HttpServletRequest req) {
		var pay = req.getParameter("payment");
		var tName = req.getParameter("tName");
		var tNum = req.getParameter("tNum");
		var amo = req.getParameter("amount");
		var note = req.getParameter("note");
		
		var payType = (null != pay && !pay.equals("---")) ? PaymentType.valueOf(pay) : null;
		var amount = (null != amo ) ? Integer.valueOf(amo) : 0 ;
		
		var pk = new RegisterPk(payType,LocalDate.now(),LocalTime.now());
		var payment = new Payment();
		payment.setId(pk);
		payment.setTransferFrom(tName);
		payment.setTransactionNum(tNum);
		payment.setAmount(amount);
		payment.setNote(note);
	//	payment.setUniInfo(uni);
		pService.save(payment);
	}
	
}
