package com.mkt.ym.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "uni_info_tbl")
public class UniversityInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int rollNumber;
	
	@Enumerated(EnumType.STRING)
	private Major major;


	@OneToOne(mappedBy = "university")
	private Student student;
	
	
	@ElementCollection(targetClass = Year.class)
	@CollectionTable(name = "uni_year_tbl",joinColumns = {@JoinColumn(name = "uni_id")})
	@Enumerated(EnumType.STRING)
	private List<Year> years;
	
	public enum Major{
		JST,CE,ECE,PRE,AME
	}

	public enum Year {
		FIRST, SECOND, THIRD, FOURTH, FIFTH
	}

}
