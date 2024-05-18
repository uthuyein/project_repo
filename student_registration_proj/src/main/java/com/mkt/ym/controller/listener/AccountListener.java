package com.mkt.ym.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AccountListener implements HttpSessionListener{
	
//	private AccountService service;
//	
//	@Override
//	public void sessionCreated(HttpSessionEvent se) {
//		
//		service = AccountService.getAccountService();
//		var accounts = service.search(null);
//		
//		var session = se.getSession();
//		session.setAttribute("accounts", accounts);
//	}

	
}
