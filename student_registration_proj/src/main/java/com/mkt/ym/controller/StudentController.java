package com.mkt.ym.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mkt.ym.entity.Address;
import com.mkt.ym.entity.Parent;
import com.mkt.ym.entity.SchoolInfo;
import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.UniYear;
import com.mkt.ym.services.StudentService;
import com.mkt.ym.services.UniversityInfoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = { "/admin/studentList","/admin/addStudent" })
@MultipartConfig
public class StudentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentService stuService;
	private UniversityInfoService uniService;
	private List<UniversityInfoDto> listUniInfo;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		listUniInfo = new ArrayList<UniversityInfoDto>();
		uniService = UniversityInfoService.getUniversityInfoService();
		stuService = StudentService.getStudentService();
		

		switch (req.getServletPath()) {
		case "/admin/addStudent":
			req.getRequestDispatcher("/admin/add-student.jsp").forward(req, resp);
			break;
		case "/admin/studentList":
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
			var info = searchStudentFromUni(req);
			listUniInfo = uniService.searchUniversityInfo(info);
			req.setAttribute("listUniInfo", listUniInfo);
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
	
	
	private UniversityInfoDto searchStudentFromUni(HttpServletRequest req) {
		var fYear = req.getParameter("openYear");

		var uYear = req.getParameter("uniYear");
		var maj = req.getParameter("major");
		var name = req.getParameter("stuName");

		var openYear = !fYear.equals("---") ? Integer.valueOf(fYear) : null;		
		var uniYear = !uYear.equals("---") ? UniYear.valueOf(uYear) : null;
		var major = !maj.equals("---") ? Major.valueOf(maj) : null;

		var info = new UniversityInfoDto(openYear, uniYear, major, name);
		return info;

	}

}
