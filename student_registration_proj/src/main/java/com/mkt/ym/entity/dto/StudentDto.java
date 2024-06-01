package com.mkt.ym.entity.dto;

import java.time.LocalDate;

public record StudentDto(
		Integer id,
		String name,
		String email,
		String primaryContact,
		String secondaryContact,
		LocalDate dob,
		String image,
		String nrc,
		String religion,
		Integer schoolId,
		String rollNum,
		Integer totalMarks,
		Integer parentId,
		String fName,
		String mName,
		String fNrc,
		String mNrc,
		Integer addressId,
		String city,
		String township,
		String street
		
		) {
		public StudentDto(String city,String township,String name){
			this(null, name, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, city, township, null);
		}
}
