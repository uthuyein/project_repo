package com.mkt.ym.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.mkt.ym.entity.Payment;
import com.mkt.ym.entity.PaymentPk;
import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.UniversityInfo;
import com.mkt.ym.entity.UniversityInfoPK;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.Message;
import com.mkt.ym.entity.type.PaymentType;
import com.mkt.ym.entity.type.UniYear;
import com.mkt.ym.services.PaymentService;
import com.mkt.ym.services.UniversityInfoService;
import com.mkt.ym.utils.StuRegException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/student/addPayment" })
public class PaymentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PaymentService payService;
	private UniversityInfoService uniService;
	private Message message;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		payService = PaymentService.getPaymentService();
		uniService = UniversityInfoService.getUniversityInfoService();

		req.getRequestDispatcher("/student/addPayment.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		savePayment(req, resp);
	}

	private void savePayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			var open = req.getParameter("openYear");
			var uni = req.getParameter("uniYear");
			var maj = req.getParameter("major");

			var pay = req.getParameter("payment");
			var tName = req.getParameter("tName");
			var tNum = req.getParameter("tNum");
			var amo = req.getParameter("amount");
			var roll = req.getParameter("rollNumber");
			var note = req.getParameter("note");

			if (!amo.matches("[0-9]+")) {
				throw new StuRegException("Amount must be digit only !");
			}

			UniversityInfoDto uniInfoDto = (UniversityInfoDto) req.getSession().getAttribute("uniInfoDto");
			var uniInfoDtonNew = new UniversityInfoDto(Integer.valueOf(open), UniYear.valueOf(uni), Major.valueOf(maj),
					 roll,uniInfoDto.name());

			var uniInfoDtoTwo = uniService.searchUniversityInfo(uniInfoDtonNew);
			
			if (null == uniInfoDtoTwo || uniInfoDtoTwo.isEmpty()) {
				throw new StuRegException("There is no student in university of year !");
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
			var student = new Student();
			student.setId(null);
			var uniInfo = new UniversityInfo();

			var uniPk = new UniversityInfoPK();
			uniPk.setMajor(uniInfoDtoTwo.get(0).major());
			uniPk.setOpenYear(uniInfoDtoTwo.get(0).openYear());
			uniPk.setRollNumber(uniInfoDtoTwo.get(0).rollNumber());
			uniPk.setUniYear(uniInfoDtoTwo.get(0).uniYear());

			uniInfo.setStudent(student);
			uniInfo.setId(uniPk);
			payment.setUniInfo(uniInfo);
			payService.save(payment);

			message = Message.SUCCESS;
			message.setMessage("Successfully save payment !");

		} catch (Exception e) {
			message = Message.ERROR;
			message.setMessage(e.getMessage());
		}

		req.setAttribute("message", message);
		req.getRequestDispatcher("/student/addPayment.jsp").forward(req, resp);

	}

}
