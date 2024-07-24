package com.mkt.ym.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.mkt.ym.entity.Messenger;
import com.mkt.ym.entity.Payment;
import com.mkt.ym.entity.PaymentPk;
import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.UniversityInfo;
import com.mkt.ym.entity.UniversityInfoPK;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.MessageType;
import com.mkt.ym.entity.type.PaymentType;
import com.mkt.ym.entity.type.UniYear;
import com.mkt.ym.services.MessengerService;
import com.mkt.ym.services.PaymentService;
import com.mkt.ym.services.UniversityInfoService;
import com.mkt.ym.utils.StuRegException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/student/addPayment", "/admin/paymentList", "/admin/updatePayment" })
public class PaymentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PaymentService payService;
	private UniversityInfoService uniService;
	private MessageType message;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		payService = PaymentService.getPaymentService();
		uniService = UniversityInfoService.getUniversityInfoService();
		var mService = MessengerService.getMessengerService();
		
		switch (req.getServletPath()) {
		case "/student/addPayment":
			Messenger mm = new Messenger(new Student(null != req.getParameter("id")? Integer.valueOf(req.getParameter("id")): null));
			var messengers = mService.search(mm);
			req.setAttribute("messengers", messengers);		
			req.getRequestDispatcher("/student/addPayment.jsp").forward(req, resp);
			break;
		case "/admin/updatePayment":
			var pay = new Payment();
			var pk = new PaymentPk();
			pk.setPaymentType(PaymentType.valueOf(req.getParameter("type")));
			pk.setTransactionDate(LocalDate.parse(req.getParameter("date")));
			pk.setTransactionTime(LocalTime.parse(req.getParameter("time")));
			pay.setId(pk);
			var payment = payService.search(pay).get(0);
			payment.setStatus(Boolean.valueOf(req.getParameter("status")));
		
			MessageType m = null;
			if (payment.getStatus()) {
				m = MessageType.SUCCESS;
				m.setMessage("""
						Thanks %s , you have already payment for year of %s and roll number  %s .
						""".formatted(payment.getUniInfo().getStudent().getName(),payment.getUniInfo().getId().getUniYear(),payment.getUniInfo().getId().getRollNumber()));		
			} else {
				m = MessageType.ERROR;
				m.setMessage("""
						Dear %s , your payment information was something wrong for year of %s and roll number  %s .Please re-fill of your payment information.
						""".formatted(payment.getUniInfo().getStudent().getName(),payment.getUniInfo().getId().getUniYear(),payment.getUniInfo().getId().getRollNumber()));				
			}
			
			var messenger = new Messenger();
			messenger.setMessage(m);
			messenger.setText(m.getMessage());
			messenger.setStudent(payment.getUniInfo().getStudent());
			payment.addMessage(messenger);
			payService.update(payment);
			searchPayment(req, resp, null);
			break;
		case "/admin/paymentList":
			searchPayment(req, resp, null);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case "/student/addPayment":
			savePayment(req, resp);
			break;
		case "/admin/paymentList":
			var pay = getPayment(req);
			searchPayment(req, resp, pay);
			break;
		}
	}

	private Payment getPayment(HttpServletRequest req) {
		var ope = req.getParameter("openYear");
		var uni = req.getParameter("uniYear");
		var maj = req.getParameter("major");
		var stat = req.getParameter("status");
		var typ = req.getParameter("payment");
		var name = req.getParameter("stuName");
		var openYear = null != ope ? Integer.parseInt(ope) : null;
		var uniYear = null != uni ? UniYear.valueOf(uni) : null;
		var major = null != maj ? Major.valueOf(maj) : null;
		var payType = null != typ ? PaymentType.valueOf(typ) : null;
		var status = (null != stat && !stat.isEmpty()) ? Boolean.valueOf(stat) : null;

		var student = new Student();
		student.setName(name);

		var uniPk = new UniversityInfoPK(openYear, null, major, uniYear);
		var uniInfo = new UniversityInfo(uniPk, null);

		var pk = new PaymentPk(payType, null, null);
		var pay = new Payment();
		pay.setId(pk);
		pay.setStatus(status);
		pay.setUniInfo(uniInfo);
		return pay;
	}

	private void searchPayment(HttpServletRequest req, HttpServletResponse resp, Payment payment)
			throws ServletException, IOException {
		var payments = payService.search(payment);
		req.setAttribute("payments", payments);
		req.getRequestDispatcher("/admin/listPayment.jsp").forward(req, resp);
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
					roll, uniInfoDto.name());

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

			message = MessageType.SUCCESS;
			message.setMessage("Successfully save payment !");

		} catch (Exception e) {
			message = MessageType.ERROR;
			message.setMessage(e.getMessage());
		}

		req.setAttribute("message", message);
		req.getRequestDispatcher("/student/addPayment.jsp").forward(req, resp);

	}

}
