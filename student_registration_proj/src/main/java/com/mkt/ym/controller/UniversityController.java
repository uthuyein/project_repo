package com.mkt.ym.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.UniversityInfo;
import com.mkt.ym.entity.UniversityInfoPK;
import com.mkt.ym.entity.dto.StudentDto;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.Message;
import com.mkt.ym.entity.type.UniYear;
import com.mkt.ym.services.UniversityInfoService;
import com.mkt.ym.utils.StuRegException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/addStudentToUni", "/admin/studentListfrmUni", })
public class UniversityController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UniversityInfoService uniService;
	private List<UniversityInfoDto> listUniInfo;
	private static List<StudentDto> listStudent;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		listUniInfo = new ArrayList<UniversityInfoDto>();

		uniService = UniversityInfoService.getUniversityInfoService();
		listStudent = (List<StudentDto>) req.getAttribute("listStudent");

		switch (req.getServletPath()) {
		case "/admin/addStudentToUni":
			req.getRequestDispatcher("/admin/addUniInfo.jsp").forward(req, resp);
			break;
		case "/admin/studentListfrmUni":
			req.getRequestDispatcher("/admin/listUniInfo.jsp").forward(req, resp);
			break;

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		switch (req.getServletPath()) {
		case "/admin/addStudentToUni":
			saveUniInfo(req, resp);
			break;
		case "/admin/studentListfrmUni":
			var info = searchStudentFromUni(req);
			listUniInfo = uniService.searchUniversityInfo(info);
			req.setAttribute("listUniInfo", listUniInfo);
			req.getRequestDispatcher("/admin/listUniInfo.jsp").forward(req, resp);
			break;
		}
	}

	private void saveUniInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			var uniInfo = getUniInfo(req);
			uniService.save(uniInfo);
			resp.sendRedirect(req.getServletPath());
		} catch (Exception e) {
			Message message = Message.ERROR;
			message.setMessage(e.getMessage());
			req.setAttribute("message", message);
			req.getRequestDispatcher("/admin/addUniInfo.jsp").forward(req, resp);			
		}
	}
	
	

	private UniversityInfo getUniInfo(HttpServletRequest req) {
		try {
			var oYear = req.getParameter("openYear");
			var maj = req.getParameter("major");
			var newRoll = req.getParameter("newRollNum");
			var uYear = req.getParameter("uniYear");
			
			var major = (null != maj) ? Major.valueOf(maj) : null;
			var uniYear = (null != uYear) ? UniYear.valueOf(uYear) : null;
			var openYear = (oYear != null) ? Integer.valueOf(oYear) : LocalDate.now().getYear();

			var uniInfo = new UniversityInfo();
			var uniPk = new UniversityInfoPK(openYear, newRoll, major, uniYear);

			var stuDto = getStudent(req.getParameter("stuName"), req.getParameter("nrc")).get();
			if (null == stuDto) {
				throw new StuRegException("Student name and student nrc did not match. Please try again !");
			}
			
			listUniInfo = uniService
					.searchUniversityInfo(new UniversityInfoDto(openYear, uniYear, major, stuDto.name()));
			System.out.println("============================================"+listUniInfo);
			if (null != listUniInfo && listUniInfo.size() > 0 ) {
				throw new StuRegException("Student name of this university year is already register !");
			}

			uniInfo.setId(uniPk);
			var student = new Student();
			student.setId(stuDto.id());
			uniInfo.setStudent(student);
			return uniInfo;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new StuRegException(e.getMessage());
		}
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

	private Optional<StudentDto> getStudent(String name, String nrc) {
		return listStudent.stream().filter(s -> s.name().equalsIgnoreCase(name) && s.nrc().equalsIgnoreCase(nrc))
				.findFirst();
	}

}
