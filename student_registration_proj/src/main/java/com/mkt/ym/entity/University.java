package com.mkt.ym.entity;

import java.util.List;

import com.mkt.ym.entity.type.Major;
import com.mkt.ym.entity.type.Year;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "university_tbl")
public class University {
	
	@EmbeddedId
	private UniversityPK id;
	
	@Enumerated(EnumType.STRING)
	private Major major;

	@ManyToOne
	private Student student;
	
	
	@ElementCollection(targetClass = Year.class)
	@CollectionTable(name = "uni_year_tbl")
	@Enumerated(EnumType.STRING)
	private List<Year> years;

}
