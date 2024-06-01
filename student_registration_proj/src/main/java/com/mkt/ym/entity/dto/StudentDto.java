package com.mkt.ym.entity.dto;

import java.time.LocalDate;

public record StudentDto(
		String name,
		String email,
		String primaryContact,
		String secondaryContact,
		LocalDate dob,
		String image,
		String nrc,
		String religion,
		String rollNum,
		int totalMarks,
		String fName,
		String mName,
		String fNrc,
		String mNrc,
		String city,
		String township,
		String street
		
		) {

}
