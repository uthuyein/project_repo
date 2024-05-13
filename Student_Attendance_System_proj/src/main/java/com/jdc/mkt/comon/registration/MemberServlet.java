package com.jdc.mkt.comon.registration;

import java.io.IOException;

import com.jdc.mkt.ds.Member;
import com.jdc.mkt.ds.Role;
import com.jdc.mkt.service.MemberService;
import com.jdc.mkt.service.impl.MemberServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/add-member", "/members/list-members", "/save-member", "/admin/edit-member",
		"/admin/delete-member" })
public class MemberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private MemberService mService;

	@Override
	public void init() throws ServletException {
		mService = new MemberServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("members", mService.getMember(0, null));
		int id = convertToInt(req.getParameter("id"));

		switch (req.getServletPath()) {
		case "/members/list-members":
		case "/admin/add-member":
			req.getRequestDispatcher("/header").include(req, resp);
			req.getRequestDispatcher(req.getServletPath().concat(".jsp")).include(req, resp);
			req.getRequestDispatcher("/footer").include(req, resp);
			break;
		case "/admin/edit-member":
			Member m = mService.getMember(id, null).stream().findFirst().orElse(null);
			req.setAttribute("member", m);
			req.getRequestDispatcher("/admin/add-member").forward(req, resp);
			break;
		case "/admin/delete-member":
			mService.deleteMember(id);
			resp.sendRedirect(req.getContextPath().concat("/members/list-members"));
			break;

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("mName");
		String pass = req.getParameter("pass");
		Role role = Role.valueOf(req.getParameter("role").toUpperCase());
		int id = convertToInt(req.getParameter("id"));

		Member m = new Member();
		m.setName(name);
		m.setPassword(pass);
		m.setRole(role);
		m.setActive(true);

		if (id > 0) {
			m.setId(id);
			mService.updateMember(m);
		} else {
			mService.saveMember(m);
		}

		resp.sendRedirect(req.getContextPath().concat("/members/list-members"));
	}

	private int convertToInt(String str) {
		return str == null ? 0 : Integer.parseInt(str);
	}

}
