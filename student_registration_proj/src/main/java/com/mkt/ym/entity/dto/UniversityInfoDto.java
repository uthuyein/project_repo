package com.mkt.ym.entity.dto;

import java.time.LocalDate;

import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.UniYear;

public record UniversityInfoDto (
		Integer uniOpenYear,
		UniYear uniYear,
		Major major,
		String rollNumber,
		String name,
		String email,
		String primaryPhone,
		String secondaryPhone,
		LocalDate dob,
		String image,
		String nrc,
		String religion,
		String schoolRollnum,
		Integer schoolTotalMarks,
		String fName,
		String mName,
		String fNrc,
		String mNrc,
		String city,
		String township,
		String street
		){
	
	public UniversityInfoDto(Integer uniOpenYear,UniYear uniYear,Major major,String name) {
		this(uniOpenYear, uniYear, major, null, name, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}

}
