package com.mkt.ym.utils;

import jakarta.servlet.http.HttpServletRequest;

public class NrcConverter {

	public static String getNrc(String name, HttpServletRequest req) {
		var code = req.getParameter(name + "code");
		var codeName = req.getParameter(name + "codeName");
		var codeNum = req.getParameter(name + "codeNum");
		if(codeNum.length() != 6) {
			throw new StuRegException("Nrc number length must be 6 !");
		}
		var nrc = code + "/" + codeName + " (N)" + codeNum;
		return nrc;
	}

	public static void setNrc(String name, String nrc, HttpServletRequest req) {
		String[] arr = nrc.split("/");
		String[] arr2 = arr[1].split(" ");
		String code = arr[0];
		String nameMM = arr2[0];
		String codeNum = arr2[1].substring(3);
		System.out.println("code ::::" + code + "\tcode name :::::" + nameMM + "\tcode num ::::" + codeNum);
		req.setAttribute(name + "code", code);
		req.setAttribute(name + "codeName", nameMM);
		req.setAttribute(name + "codeNum", codeNum);
	}

}
