package com.mkt.ym.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.Year;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class UniversityPK implements Serializable{

	private static final long serialVersionUID = 1L;

	private LocalDate uniYearId;	
	private String rollNumberId;
	
	@Enumerated(EnumType.ORDINAL)
	private Major majorId;
	@Enumerated(EnumType.ORDINAL)
	private Year yearId;
	
	
}
