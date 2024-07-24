package com.mkt.ym.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mkt.ym.entity.Address;
import com.mkt.ym.entity.Messenger;
import com.mkt.ym.entity.Parent;
import com.mkt.ym.entity.SchoolInfo;
import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.dto.StudentDto;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.entity.type.MessageType;
import com.mkt.ym.services.MessengerService;
import com.mkt.ym.services.StudentService;
import com.mkt.ym.utils.StuRegException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import static com.mkt.ym.utils.NrcConverter.getNrc;
import static com.mkt.ym.utils.NrcConverter.setNrc;;

@WebServlet(urlPatterns = { "/student/detailStudent", "/admin/studentList", "/admin/editStudent",
		"/admin/deleteStudent", "/admin/addStudent", "/student/messenger", "/student/deleteMessenges" })
@MultipartConfig
public class StudentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentService stuService;
	private MessengerService mService;
	private MessageType message;
	private static List<Messenger> messengers = new ArrayList<Messenger>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		stuService = StudentService.getStudentService();
		mService = MessengerService.getMessengerService();

		var sId = req.getParameter("id");		
		var id = (null != sId && !sId.isEmpty()) ? Integer.valueOf(sId) : null;
		
		switch (req.getServletPath()) {
		case "/student/detailStudent":
			setAttributeMessage(req, id);
			req.getRequestDispatcher("/student/detailStudent.jsp").forward(req, resp);
			break;

		case "/student/messenger":
			setAttributeMessage(req, id);
			req.getRequestDispatcher("/student/messenger.jsp").forward(req, resp);
			break;

		case "/admin/addStudent":
			req.getRequestDispatcher("/admin/addStudent.jsp").forward(req, resp);
			break;

		case "/admin/editStudent":
			var stuDto = new StudentDto(id);
			var studentDto = stuService.searchStudentDto(stuDto).get(0);
			setNrc("", studentDto.nrc(), req);
			setNrc("f", studentDto.fNrc(), req);
			setNrc("m", studentDto.mNrc(), req);
			req.setAttribute("studentDto", studentDto);
			req.getRequestDispatcher("/admin/addStudent.jsp").forward(req, resp);
			break;

		case "/admin/deleteStudent":
			var student = new Student();
			student.setId(id);
			stuService.delete(student);
			var listStudentDto = stuService.searchStudentDto(null);
			req.setAttribute("listStudentDto", listStudentDto);
			req.getRequestDispatcher("/admin/listStudent.jsp").forward(req, resp);
			break;

		case "/admin/studentList":
			req.getRequestDispatcher("/admin/listStudent.jsp").forward(req, resp);
			break;

		case "/student/deleteMessenges":
			var mId = (null != req.getParameter("messengerId")) ? Integer.valueOf(req.getParameter("messengerId")) : null;
			mService.delete(new Messenger(mId));
			setAttributeMessage(req, id);
			req.getRequestDispatcher("/student/messenger.jsp").forward(req, resp);
			
			break;
		}
	}
	
	private void setAttributeMessage(HttpServletRequest req,Integer StudentId) {
		Messenger m = new Messenger(new Student(StudentId));
		messengers = mService.search(m);
		req.setAttribute("messengers", messengers);		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case "/admin/addStudent":
		case "/admin/editStudent":
			saveStudent(req, resp);
			break;

		case "/admin/studentList":
			var dto = searchStudent(req);
			var listStudentDto = stuService.searchStudentDto(dto);
			req.setAttribute("listStudentDto", listStudentDto);
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
			message = MessageType.SUCCESS;

			if (null == student.getId()) {
				stuService.save(student);
				message.setMessage("Successfully save student !");
			} else {
				stuService.update(student);
				message.setMessage("Successfully update student !");
			}

		} catch (Exception e) {
			message = MessageType.ERROR;
			message.setMessage(e.getMessage());

		}
		req.setAttribute("message", message);
		req.getRequestDispatcher("/admin/addStudent.jsp").forward(req, resp);

	}

	private Student getStudent(HttpServletRequest req) {
		try {
			var id = req.getParameter("stuId");
			var name = req.getParameter("stuName");
			var religion = req.getParameter("religion").toLowerCase().replaceAll(" ", "");
			var date = LocalDate.parse(req.getParameter("dob"));
			var email = req.getParameter("email");
			var pContact = req.getParameter("pContact").toLowerCase().replaceAll(" ", "");
			var sContact = req.getParameter("sContact").toLowerCase().replaceAll(" ", "");

			if (!pContact.matches("[0-9]+") || !sContact.matches("[0-9]+")) {
				throw new StuRegException("Phone number must be digit only");
			}
			if (pContact.length() < 6 || pContact.length() > 12) {
				System.out.println(" primary number 6 Testing");
				throw new StuRegException("Phone number must between 6 and 12 !");
			}
			if (sContact.length() < 6 || sContact.length() > 12) {
				throw new StuRegException("Phone number must between 6 and 12 !");
			}

			var nrc = getNrc("", req);
			var image = getFile(req).getFileName().toString();
			var student = new Student(name, date, religion, image, nrc, email, pContact, sContact);
			student.setId(null != id ? Integer.valueOf(id) : null);
			return student;

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
			var fNrc = getNrc("f", req);
			var mNrc = getNrc("m", req);
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
		} catch (Exception e) {
			throw new StuRegException(e.getMessage());
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
