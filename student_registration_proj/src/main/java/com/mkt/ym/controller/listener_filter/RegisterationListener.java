package com.mkt.ym.controller.listener_filter;

import java.util.List;
import java.util.stream.Collectors;

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
		
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		uniService = UniversityInfoService.getUniversityInfoService();
		stuService = StudentService.getStudentService();
		
		listUniInfo = uniService.searchUniversityInfo(null);
		var students = stuService.search(null);
		var session = req.getSession(true);
		
		if (null != students) {
			session.setAttribute("listStudent", students);
		}

		if (null != listUniInfo) {
			
			session.setAttribute("openYears", getYear());
			session.setAttribute("listUniInfo", listUniInfo);
			session.setAttribute("cities", getCities());
			session.setAttribute("townships", getTownships());
		}
	}

	private List<Integer> getYear() {
		return  listUniInfo.stream()
				.map(UniversityInfoDto::openYear)
				.distinct().sorted().collect(Collectors.toList());
	}
	
	private List<String> getCities() {
		return  listUniInfo.stream().map(UniversityInfoDto::city).distinct().collect(Collectors.toList());
	}
	private List<String> getTownships() {
		return  listUniInfo.stream().map(UniversityInfoDto::township).distinct().collect(Collectors.toList());
	}

}
