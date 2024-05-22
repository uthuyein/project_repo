package com.mkt.ym.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "university_tbl")
@NoArgsConstructor
public class UniversityInfo {
	
	@EmbeddedId
	private UniversityInfoPK id;
	@ManyToOne
	private Student student;

	public UniversityInfo(Student student) {
		super();
		this.student = student;
	}
	
	

}
