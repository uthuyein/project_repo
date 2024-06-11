package com.mkt.ym.controller.listener_filter;

import java.util.List;
import java.util.stream.Collectors;

import com.mkt.ym.entity.dto.StudentDto;
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
	private List<StudentDto> listStudent;


	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		uniService = UniversityInfoService.getUniversityInfoService();
		stuService = StudentService.getStudentService();
		
		listUniInfo = uniService.searchUniversityInfo(null);
		listStudent = stuService.searchStudentDto(null);
		
		if (null != listStudent) {
			req.setAttribute("listStudent", listStudent);
		}

		if (null != listUniInfo) {
			
			req.setAttribute("openYears", getYear());
			req.setAttribute("listUniInfo", listUniInfo);
			req.setAttribute("cities", getCities());
			req.setAttribute("townships", getTownships());
		}
	}

	private List<Integer> getYear() {
		return  listUniInfo.stream()
				.map(UniversityInfoDto::openYear)
				.distinct().sorted().collect(Collectors.toList());
	}
	
	private List<String> getCities() {
		return  listStudent.stream().map(StudentDto::city).distinct().collect(Collectors.toList());
	}
	private List<String> getTownships() {
		return  listStudent.stream().map(StudentDto::township).distinct().collect(Collectors.toList());
	}

}
