package com.mkt.ym.entity.dto;

import java.time.LocalDate;

import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.UniYear;

public record UniversityInfoDto (
		Integer openYear,
		UniYear uniYear,
		Major major,
		String rollNumber,
		Integer stuId,
		String name,
		String email,
		String primaryPhone,
		String secondaryPhone,
		LocalDate dob,
		String image,
		String nrc,
		String religion,
		Integer schoolId,
		String schoolRollnum,
		Integer schoolTotalMarks,
		Integer parentId,
		String fName,
		String mName,
		String fNrc,
		String mNrc,
		Integer addressId,
		String city,
		String township,
		String street
		){
	
	
	public UniversityInfoDto(Integer openYear,UniYear uniYear,Major major,String name) {
		this(openYear, uniYear, major, null,null, name, null, null,null,null,null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	public UniversityInfoDto(Integer openYear,UniYear uniYear,Major major, String stuName, LocalDate dob, String nrc, String fNrc, String mNrc,
			String schEnroll, int schMarks) {		
		this(openYear, uniYear, major, null,null, stuName, null, null,null,dob,null, nrc, null, null, schEnroll, schMarks, null, null, null, fNrc, mNrc, null, null, null, null);
	}

}
