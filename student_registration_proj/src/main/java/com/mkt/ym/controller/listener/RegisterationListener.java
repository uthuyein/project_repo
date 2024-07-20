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
	//private List<NrcDto> nrcs;
	
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		uniService = UniversityInfoService.getUniversityInfoService();
		stuService = StudentService.getStudentService();
		listUniInfo = uniService.searchUniversityInfo(null);
		listStudentDto = stuService.searchStudentDto(null);
	//	nrcs = getNrcFromJson();
		if (null != listStudentDto) {
			req.setAttribute("listStudentDto", listStudentDto);
		}

		req.setAttribute("openYears", getYear());
		req.setAttribute("listUniInfo", listUniInfo);
		req.setAttribute("cities", getCities());
		req.setAttribute("townships", getTownships());
		System.out.println("==================================== new Requert");
//		var type = req.getParameter("type");
	//	var temp = getNrcs(req.getParameter(type+"code"), req.getParameter(type+"codeName"), nrcs);	
	//	req.setAttribute("nrcs",temp);
		
		req.setAttribute("nrcCodes", getNrcCodes());
		//req.setAttribute("nrcCodeNames", getNrcCodeNames());
		
	}

	private List<Integer> getYear() {
		return listUniInfo.stream().map(UniversityInfoDto::openYear).distinct().sorted().collect(Collectors.toList());
	}

	private List<String> getCities() {
		return listStudentDto.stream().map(StudentDto::city).distinct().collect(Collectors.toList());
	}

	private List<String> getTownships() {
		return listStudentDto.stream().map(StudentDto::township).distinct().collect(Collectors.toList());
	}

//	public List<NrcDto> getNrcFromJson() {
//		try {
//			ObjectMapper mapper = new ObjectMapper();
//			if (null == nrcs) {
//				nrcs = mapper.readValue(new File("src/main/resources/META-INF/nrc.json"),
//						new TypeReference<List<NrcDto>>() {
//						});
//			}
//			return nrcs;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
//	public List<NrcDto> getNrcs(String code, String name, List<NrcDto> nrcs) {
//		System.out.println("code :::::::"+code+"\tName ::::::::"+name);
//		if (null == code && null == name) {
//			return nrcs;
//		} else if (null != code && null == name) {
//			return nrcs.stream().filter(n -> n.nrc_code().equals(code)).toList();
//		} else if (null != name && null == code) {
//			return nrcs.stream().filter(n -> n.name_mm().equals(code)).toList();
//		} else if (null != code && null != name) {
//			return nrcs.stream().filter(n -> n.nrc_code().equals(code)).filter(n -> n.name_mm().equals(name)).toList();
//		}
//		return nrcs;
//	}
	
	public List<String> getNrcCodes() {
		return IntStream.range(1, 15).mapToObj(num -> String.valueOf(num)).collect(Collectors.toList());
	}
//
//	public List<String> getNrcCodeNames() {
//		return nrcs.stream().map(n -> n.name_en()).distinct().sorted().toList();
//	}


}
