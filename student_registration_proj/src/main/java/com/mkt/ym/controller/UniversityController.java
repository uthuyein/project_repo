package com.mkt.ym.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.UniversityInfo;
import com.mkt.ym.entity.UniversityInfoPK;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.UniYear;
import com.mkt.ym.services.UniversityInfoService;

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
	private static List<Student> students;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		listUniInfo = new ArrayList<UniversityInfoDto>();
		students = new ArrayList<Student>();
		
		uniService = UniversityInfoService.getUniversityInfoService();
		students = (List<Student>) req.getSession().getAttribute("students");
		
		switch (req.getServletPath()) {
		case "/admin/addStudentToUni":
			req.setAttribute("students", students);
			req.getRequestDispatcher("/admin/add-uni-info.jsp").forward(req, resp);
			break;
		case "/admin/studentListfrmUni":
//			listUniInfo = uniService.searchUniversityInfo(null);		
//			req.setAttribute("listUniInfo", listUniInfo);
			req.getRequestDispatcher("/admin/list-uni-info.jsp").forward(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		switch (req.getServletPath()) {
		case "/admin/addStudentToUni":
			var uniInfo = getUniInfo(req);
			uniService.save(uniInfo);
			resp.sendRedirect(req.getServletPath());
			break;
		case "/admin/studentListfrmUni":
			var info = searchStudentFromUni(req);
			listUniInfo = uniService.searchUniversityInfo(info);
			req.setAttribute("listUniInfo", listUniInfo);
			req.getRequestDispatcher("/admin/list-uni-info.jsp").forward(req, resp);
			break;
		}
	}

	private UniversityInfo getUniInfo(HttpServletRequest req) {
		var uYear = req.getParameter("openYear");
		var maj = req.getParameter("major");
		var newRoll = req.getParameter("newRollNum");
		var newY = req.getParameter("newyear");
		var student = getStudent(req.getParameter("stuName"), req.getParameter("nrc")).get();

		var major = (null != maj) ? Major.valueOf(maj) : null;
		var newYear = (null != newY) ? UniYear.valueOf(newY) : null;

		var uniYear = (uYear != null) ? Integer.valueOf(uYear) : LocalDate.now().getYear();

		var uniInfo = new UniversityInfo();
		var uniPk = new UniversityInfoPK(uniYear, newRoll, major, newYear);

		uniInfo.setId(uniPk);
		uniInfo.setStudent(student);
		return uniInfo;

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

	private Optional<Student> getStudent(String name, String nrc) {
		return students.stream().filter(s -> s.getName().equalsIgnoreCase(name) && s.getNrc().equalsIgnoreCase(nrc))
				.findFirst();
	}

}
