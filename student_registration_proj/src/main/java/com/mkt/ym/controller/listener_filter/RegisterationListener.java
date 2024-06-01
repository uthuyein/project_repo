package com.mkt.ym.controller.listener_filter;

import java.util.ArrayList;
import java.util.List;

import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.services.StudentService;
import com.mkt.ym.services.UniversityInfoService;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

@WebListener
public class RegisterationListener implements ServletRequestListener {

	private UniversityInfoService uniService;
	private StudentService stuService;
	private List<UniversityInfoDto> listUniInfo;

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		
		listUniInfo = new ArrayList<UniversityInfoDto>();
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		uniService = UniversityInfoService.getUniversityInfoService();
		stuService = StudentService.getStudentService();
		
		var listUniInfo = uniService.searchUniversityInfo(null);
		var students = stuService.search(null);
		var session = req.getSession(true);
		
		if (null != students) {
			session.setAttribute("students", students);
		}

		if (null != listUniInfo) {
			System.out.println("=================== year");
			session.setAttribute("years", getYear());
			session.setAttribute("listUniInfo", listUniInfo);

		}
	}

	private int[] getYear() {
		return  listUniInfo.stream()
				.mapToInt(uni -> uni.openYear())
				.distinct().sorted().toArray();
	}

}
