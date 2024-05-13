package com.jdc.mkt.comon.registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.mkt.ds.Address;
import com.jdc.mkt.ds.ClassRoom;
import com.jdc.mkt.ds.Student;
import com.jdc.mkt.service.ClassRoomService;
import com.jdc.mkt.service.StudentService;
import com.jdc.mkt.service.impl.ClassRoomServiceImpl;
import com.jdc.mkt.service.impl.StudentServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/members/list-students",
		"/admin/save-student",
		"/admin/add-student",
		"/admin/edit-student",
		"/admin/delete-student",
		"/select-by-classroom"})
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentService studentSevice;
	private ClassRoomService classRoomService;
	private List<Student>stuList;

	@Override
	public void init() throws ServletException {
		studentSevice = new StudentServiceImpl();
		classRoomService = new ClassRoomServiceImpl();
		stuList = new ArrayList<>();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id =convertToInt(req.getParameter("id"));
		
		switch(req.getServletPath()) {
		case "/members/list-students":
		case "/admin/add-student":
			
			req.getRequestDispatcher("/header").include(req, resp);
			req.getRequestDispatcher(req.getServletPath().concat(".jsp")).include(req, resp);
			req.getRequestDispatcher("/footer").include(req, resp);
			break;
		case "/admin/edit-student":
			Student stu = studentSevice.getStudents(id, null, null).stream().findFirst().orElse(null);
			req.setAttribute("student", stu);
			req.getRequestDispatcher("/admin/add-student").forward(req, resp);
			
			break;
		case "/admin/delete-student":
			studentSevice.deleteStudent(id);
			resp.sendRedirect(req.getContextPath().concat("/members/list-students"));
			break;
		case "/select-by-classroom":
			stuList = studentSevice.getStudents(0, null, req.getParameter("room"));		
			req.removeAttribute("students");
			req.setAttribute("students", stuList);
			req.getRequestDispatcher("/members/list-students").forward(req, resp);
			break;
		
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String city = req.getParameter("city");
		String township = req.getParameter("township");
		String street = req.getParameter("street");
		String room = req.getParameter("room");
		int sid = convertToInt(req.getParameter("sId"));
		int aid = convertToInt(req.getParameter("aId"));

		Address ad = new Address();
		ad.setCity(city);
		ad.setTownship(township);
		ad.setStreet(street);

		ClassRoom cl = classRoomService.getClassRooms(0, room).stream().findFirst().orElse(null);

		Student st = new Student();
		st.setName(name);
		st.setEmail(email);
		st.setPhone(phone);
		st.setActive(true);
		st.setClassRoom(cl);
		st.setAddress(ad);
		
		if(sid > 0) {
			ad.setId(aid);
			st.setId(sid);
			studentSevice.updateStudent(st);
		}else {
			studentSevice.saveStudent(st);
		}
		
		
		resp.sendRedirect(req.getContextPath().concat("/members/list-students"));
	}
	
	private int convertToInt(String id) {
		return id == null ? 0 : Integer.parseInt(id);
	}

}
