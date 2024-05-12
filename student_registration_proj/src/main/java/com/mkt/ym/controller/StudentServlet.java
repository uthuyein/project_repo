package com.mkt.ym.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import com.mkt.ym.entity.Address;
import com.mkt.ym.entity.Parent;
import com.mkt.ym.entity.SchoolInfo;
import com.mkt.ym.entity.Student;
import com.mkt.ym.services.StudentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = { 
		"/admin/addStudent",
		"/admin/addParent", 
		"/admin/addAddress", 
		"/admin/schoolInfo" 
		})
@MultipartConfig
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Student student;
	private StudentService service;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		service = StudentService.getStudentService();

		switch (req.getServletPath()) {
		case "/admin/addStudent":
			createStudent(req);

			req.getRequestDispatcher("/admin/add-school-info.jsp").forward(req, resp);
			break;
		case "/admin/schoolInfo":
			createSchoolInfo(req);
			
			req.getRequestDispatcher("/admin/add-parent.jsp").forward(req, resp);
			break;
		case "/admin/addParent":
			createParent(req);

			req.getRequestDispatcher("/admin/add-address.jsp").forward(req, resp);
			break;
		case "/admin/addAddress":
			createAddress(req);
			service.save(student);
			student = null;

			resp.sendRedirect("/admin/add-student.jsp");
			break;
		default:
			break;

		}
	}

	private void createSchoolInfo(HttpServletRequest req) {
		var roll = req.getParameter("rollNumber");
		var math = Integer.parseInt(req.getParameter("maths"));
		var eng = Integer.parseInt(req.getParameter("eng"));
		var phys = Integer.parseInt(req.getParameter("phys"));
		var chem = Integer.parseInt(req.getParameter("chem"));
		var ttl = Integer.parseInt( req.getParameter("ttl"));
		var school = new SchoolInfo(roll, ttl, math, phys, chem, eng);
		student.setSchoolInfo(school);
		
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

	private void createAddress(HttpServletRequest req) {
		var city = req.getParameter("city");
		var township = req.getParameter("township");
		var street = req.getParameter("street");
		var address = new Address(street, township, city);
		student.setAddress(address);
	}

	private void createParent(HttpServletRequest req) {
		var fName = req.getParameter("fName");
		var mName = req.getParameter("mName");
		var fNrc = req.getParameter("fNrc");
		var mNrc = req.getParameter("mNrc");
		var parent = new Parent(mName, fName, mNrc, fNrc);
		student.setParent(parent);
	}

	private void createStudent(HttpServletRequest req) {
		var name = req.getParameter("stuName");
		var religion = req.getParameter("religion");
		var date = LocalDate.parse(req.getParameter("dob"));
		var nrc = req.getParameter("nrc");
		var email = req.getParameter("email");
		var pContact = req.getParameter("pContact");
		var sContact = req.getParameter("sContact");

		var image = getFile(req).getFileName().toString();

		student = new Student(name, date, religion, image, nrc, email, pContact, sContact);

	}

}
