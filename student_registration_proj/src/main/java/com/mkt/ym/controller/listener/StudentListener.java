package com.mkt.ym.controller.listener;

import java.util.List;

import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.services.UniversityInfoService;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class StudentListener implements HttpSessionListener {

	private List<UniversityInfoDto> list;
	private UniversityInfoService service;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		var session = se.getSession();
		
		if(session.isNew()) {
			
		}
	}

}
