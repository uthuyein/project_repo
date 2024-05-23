package com.mkt.ym.controller.filter;

import java.io.IOException;

import com.mkt.ym.entity.Account;
import com.mkt.ym.entity.type.Role;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;


@WebFilter(urlPatterns = "/admin/*")
public class RegistrationSecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse respponse, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		var session = req.getSession();
		Account account = (Account) session.getAttribute("account");
		if(null != account && account.getRole() == Role.ADMIN) {
			chain.doFilter(request, respponse);
		}
		req.getRequestDispatcher("/index.jsp").forward(request, respponse);
	}

	

}
