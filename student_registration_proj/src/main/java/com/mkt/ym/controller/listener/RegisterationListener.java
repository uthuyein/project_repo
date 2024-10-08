package com.mkt.ym.controller.listener;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	private List<StudentDto> listStudentDto;

	@Override
	public void requestInitialized(ServletRequestEvent sre) {

		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		uniService = UniversityInfoService.getUniversityInfoService();
		stuService = StudentService.getStudentService();
		listUniInfo = uniService.searchUniversityInfo(null);
		listStudentDto = stuService.searchStudentDto(null);

		if (null != listStudentDto) {
			req.setAttribute("listStudentDto", listStudentDto);
		}

		req.setAttribute("openYears", getYear());
		req.setAttribute("listUniInfo", listUniInfo);
		req.setAttribute("nrcCodes", getNrcCodes());

	}

	private List<Integer> getYear() {
		return listUniInfo.stream().map(UniversityInfoDto::openYear).distinct().sorted().collect(Collectors.toList());
	}

	public List<String> getNrcCodes() {
		return IntStream.range(1, 15).mapToObj(num -> String.valueOf(num)).collect(Collectors.toList());
	}


}
