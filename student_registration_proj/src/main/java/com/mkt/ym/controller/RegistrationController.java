package com.mkt.ym.controller;

import java.io.IOException;
import java.time.LocalDate;

import com.mkt.ym.entity.Parent;
import com.mkt.ym.entity.SchoolInfo;
import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.UniversityInfo;
import com.mkt.ym.services.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/student/chkStudent"})
public class RegistrationController  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private StudentService service;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service = StudentService.getStudentService();
		var list = service.search(getStudent(req));
		
		if(null != list && list.size() > 0) {
			req.getRequestDispatcher("/student/add-payment.jsp").forward(req, resp);
		}
		req.getRequestDispatcher("").forward(req, resp);
	}

	private Student getStudent(HttpServletRequest req) {
		
		var uniEnroll = req.getParameter("uniEnroll");
		var stuName = req.getParameter("stuName");
		var religion = req.getParameter("religion");
		var dob = LocalDate.parse(req.getParameter("dob"));
		var nrc = req.getParameter("nrc");
		var fNrc = req.getParameter("fNrc");
		var mNrc = req.getParameter("mNrc");
		var schEnroll = req.getParameter("schEnroll");
		var schMarks = Integer.parseInt( req.getParameter("schMarks"));
		
		var student = new Student();
		student.setName(stuName);
		student.setReligion(religion);
		student.setDob(dob);
		student.setNrc(nrc);
		
		var parent = new Parent();
		parent.setFatherNrc(fNrc);
		parent.setMotherNrc(mNrc);
		
		var school = new SchoolInfo();
		school.setRollNum(schEnroll);
		school.setTotalMarks(schMarks);
		
		var uni = new UniversityInfo();
		uni.setRollNumber(uniEnroll);
		
		student.setParent(parent);
		student.setSchoolInfo(school);
		student.setUniversity(uni);
		
		return student;
		
	}

}
