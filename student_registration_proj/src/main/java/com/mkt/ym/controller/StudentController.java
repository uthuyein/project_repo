package com.mkt.ym.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import com.mkt.ym.entity.Address;
import com.mkt.ym.entity.Parent;
import com.mkt.ym.entity.SchoolInfo;
import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.dto.StudentDto;
import com.mkt.ym.services.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = { "/student/studentInfo","/admin/studentList","/admin/addStudent" })
@MultipartConfig
public class StudentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentService stuService;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		stuService = StudentService.getStudentService();
		

		switch (req.getServletPath()) {
		case "/student/studentInfo":
			req.getRequestDispatcher("/student/student-info.jsp").forward(req, resp);
			break;
		case "/admin/addStudent":
			req.getRequestDispatcher("/admin/add-student.jsp").forward(req, resp);
			break;
		case "/admin/studentList":
			var studentList = stuService.searchStudentDto(null);
			req.setAttribute("studentList", studentList);
			req.getRequestDispatcher("/admin/list-student.jsp").forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		switch (req.getServletPath()) {
		case "/admin/addStudent":
			var student = getStudent(req);
			var school = getSchoolInfo(req);
			var parent = getParent(req);
			var address = getAddress(req);
			student.setSchoolInfo(school);
			student.setParent(parent);
			student.setAddress(address);
			stuService.save(student);
			resp.sendRedirect("/admin/add-student.jsp");
			break;
		case "/admin/studentList":
			var dto = searchStudent(req);
			var studentList = stuService.searchStudentDto(dto);
			req.setAttribute("studentList", studentList);
			req.getRequestDispatcher("/admin/list-student.jsp").forward(req, resp);
			break;

		}

	}

	private Student getStudent(HttpServletRequest req) {
		var name = req.getParameter("stuName");
		var religion = req.getParameter("religion");
		var date = LocalDate.parse(req.getParameter("dob"));
		var nrc = req.getParameter("nrc");
		var email = req.getParameter("email");
		var pContact = req.getParameter("pContact");
		var sContact = req.getParameter("sContact");

		var image = getFile(req).getFileName().toString();
		return new Student(name, date, religion, image, nrc, email, pContact, sContact);

	}

	private Address getAddress(HttpServletRequest req) {
		var city = req.getParameter("city");
		var township = req.getParameter("township");
		var street = req.getParameter("street");
		return new Address(street, township, city);

	}

	private Parent getParent(HttpServletRequest req) {
		var fName = req.getParameter("fName");
		var mName = req.getParameter("mName");
		var fNrc = req.getParameter("fNrc");
		var mNrc = req.getParameter("mNrc");
		return new Parent(mName, fName, mNrc, fNrc);

	}

	private SchoolInfo getSchoolInfo(HttpServletRequest req) {
		var roll = req.getParameter("rollNumber");
		var ttl = Integer.parseInt(req.getParameter("ttl"));
		return new SchoolInfo(roll, ttl);

	}

	private Path getFile(HttpServletRequest req) {
		try {
			Part part = req.getPart("imageFile");

			if (null != part) {
				var file = Path.of(getServletContext().getRealPath("/images"), part.getSubmittedFileName());
				if (!file.toFile().exists()) {
					Files.copy(part.getInputStream(), file);
				}
				return file;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private StudentDto searchStudent(HttpServletRequest req) {
		var city = req.getParameter("city");
		var township = req.getParameter("township");
		var name = req.getParameter("stuName");

		var dto = new StudentDto((city.equals("---"))?null:city, (township.equals("---"))?null:township, name);
		
		return dto;

	}

}
