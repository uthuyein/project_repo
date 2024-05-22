package com.mkt.ym.entity;

import java.io.Serializable;

import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.UniYear;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UniversityInfoPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer uniOpenYear;	
	private String rollNumber;
	
	@Enumerated(EnumType.STRING)
	private Major major;

	@Enumerated(EnumType.STRING)
	private UniYear uniYear;

	public UniversityInfoPK(Integer uniOpenYear, String rollNumber, UniYear uniYear,Major major) {
		super();
		this.uniOpenYear = uniOpenYear;
		this.rollNumber = rollNumber;
		this.uniYear = uniYear;
		this.major = major;
	}
	
	
}
