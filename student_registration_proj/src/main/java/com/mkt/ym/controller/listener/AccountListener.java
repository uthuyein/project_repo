package com.mkt.ym.controller.listener;

import com.mkt.ym.services.AccountService;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AccountListener implements ServletContextListener{
	
	private AccountService service;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		service = AccountService.getAccountService();
		var accounts = service.search(null);
		
		var attr = sce.getServletContext();
		attr.setAttribute("accounts", accounts);
		
		
	}
}
