package com.mkt.ym.controller;

import java.io.IOException;
import java.time.LocalDate;

import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.UniversityInfo;
import com.mkt.ym.entity.UniversityInfoPK;
import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.UniYear;
import com.mkt.ym.services.StudentService;
import com.mkt.ym.services.UniversityInfoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/addUniInfo", "/admin/listUniversityInfo" })
public class UniversityController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentService stuService;
	private UniversityInfoService uniService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		stuService = StudentService.getStudentService();
		var session = req.getSession(true);

		switch (req.getServletPath()) {
		case "/admin/addUniInfo":
			var students = stuService.search(null);
			session.setAttribute("students", students);
			req.getRequestDispatcher("/admin/add-uni-info.jsp").forward(req, resp);
			break;

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		uniService = UniversityInfoService.getUniversityInfoService();

		switch (req.getServletPath()) {
		case "/admin/addUniInfo":
			addStudentToUni(req);
			req.getRequestDispatcher("/admin/add-uni-info.jsp").forward(req, resp);
			break;
		}
	}

	private void addStudentToUni(HttpServletRequest req) {
		var uYear = req.getParameter("openYear");
		var maj = req.getParameter("major");
		var newRoll = req.getParameter("newRollNum");
		var newY = req.getParameter("newyear");
		var stuId = req.getParameter("student");

//		 var old = req.getParameter("isOldStudent");

		var uniYear = uYear != null ? Integer.valueOf(uYear) : LocalDate.now().getYear();
		var major = null != maj ? Major.valueOf(maj) : null;
		var newYear = null != newY ? UniYear.valueOf(newY) : UniYear.FIRST;

//		if(null != old ) {
//			var pYear = req.getParameter("prvYear");
//			var pRoll = req.getParameter("prevRollNum");
//		}

		var stu = new Student();
		stu.setId(Integer.valueOf(stuId));

		var uniPk = new UniversityInfoPK(uniYear, newRoll, newYear, major);
		var uni = new UniversityInfo(stu);
		uni.setId(uniPk);
		uniService.save(uni);
	}

}
