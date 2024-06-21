package com.mkt.ym.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import com.mkt.ym.entity.Address;
import com.mkt.ym.entity.Parent;
import com.mkt.ym.entity.SchoolInfo;
import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.dto.StudentDto;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.entity.type.Message;
import com.mkt.ym.services.StudentService;
import com.mkt.ym.utils.StuRegException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = { "/student/detailStudent", "/admin/studentList","/admin/editStudent","/admin/deleteStudent", "/admin/addStudent" })
@MultipartConfig
public class StudentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentService stuService;
	private Message message;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		stuService = StudentService.getStudentService();

		switch (req.getServletPath()) {
		case "/student/detailStudent":
			var uniInfoDto = getUniInfo(req, Integer.valueOf(req.getParameter("id")));
			req.setAttribute("uniInfoDto", uniInfoDto);
			req.getRequestDispatcher("/student/detailStudent.jsp").forward(req, resp);
			break;
			
		case "/admin/addStudent":
			req.getRequestDispatcher("/admin/addStudent.jsp").forward(req, resp);
			break;
			
		case "/admin/studentList":
			req.getRequestDispatcher("/admin/listStudent.jsp").forward(req, resp);
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private UniversityInfoDto getUniInfo(HttpServletRequest req, Integer stuId) {
		var list = (List<UniversityInfoDto>) req.getAttribute("listUniInfo");
		return list.stream().filter(u -> u.stuId() == stuId).findFirst().orElse(null);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case "/admin/addStudent":
			saveStudent(req, resp);
			break;
			
		case "/admin/studentList":
			var dto = searchStudent(req);
			var studentList = stuService.searchStudentDto(dto);
			req.setAttribute("listStudent", studentList);
			req.getRequestDispatcher("/admin/listStudent.jsp").forward(req, resp);
			break;
		}

	}

	private void saveStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			var student = getStudent(req);
			var school = getSchoolInfo(req);
			var parent = getParent(req);
			var address = getAddress(req);
			student.setSchoolInfo(school);
			student.setParent(parent);
			student.setAddress(address);
			stuService.save(student);
			message = Message.SUCCESS;
			message.setMessage("Successfully save student !");

		} catch (Exception e) {
			message = Message.ERROR;
			message.setMessage(e.getMessage());

		}
		req.setAttribute("message", message);
		req.getRequestDispatcher("/admin/addStudent.jsp").forward(req, resp);

	}

	private Student getStudent(HttpServletRequest req) {
		try {
			var name = req.getParameter("stuName");
			var religion = req.getParameter("religion").toLowerCase().replaceAll(" ", "");
			var date = LocalDate.parse(req.getParameter("dob"));
			var nrc = req.getParameter("nrc").toLowerCase().replaceAll(" ", "");
			;
			var email = req.getParameter("email");
			var pContact = req.getParameter("pContact").toLowerCase().replaceAll(" ", "");
			var sContact = req.getParameter("sContact").toLowerCase().replaceAll(" ", "");

			if (!pContact.matches("[0-9]+") || !sContact.matches("[0-9]+")) {
				throw new StuRegException("Phone number must be digit only");
			}

			var image = getFile(req).getFileName().toString();
			return new Student(name, date, religion, image, nrc, email, pContact, sContact);

		} catch (Exception e) {
			throw new StuRegException(e.getMessage());
		}

	}

	private Address getAddress(HttpServletRequest req) {
		try {
			var city = req.getParameter("city");
			var township = req.getParameter("township");
			var street = req.getParameter("street");
			return new Address(street, township, city);

		} catch (Exception e) {
			throw new StuRegException(e.getMessage());
		}
	}

	private Parent getParent(HttpServletRequest req) {
		try {
			var fName = req.getParameter("fName");
			var mName = req.getParameter("mName");
			var fNrc = req.getParameter("fNrc");
			var mNrc = req.getParameter("mNrc");
			return new Parent(mName, fName, mNrc, fNrc);

		} catch (Exception e) {
			throw new StuRegException(e.getMessage());
		}
	}

	private SchoolInfo getSchoolInfo(HttpServletRequest req) {
		try {
			var roll = req.getParameter("rollNumber");
			var ttl = req.getParameter("ttl");

			if (!ttl.matches("\\d+")) {
				throw new StuRegException("Total number must be digit only !");
			}
			var total = Integer.parseInt(req.getParameter("ttl"));
			return new SchoolInfo(roll, total);

		} catch (Exception e) {
			throw new StuRegException(e.getMessage());
		}
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
		var dto = new StudentDto(city, township, name);
		return dto;

	}

}
