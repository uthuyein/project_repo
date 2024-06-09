package com.mkt.ym.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "university_tbl")
@NoArgsConstructor
@AllArgsConstructor
public class UniversityInfo {
	
	@EmbeddedId
	private UniversityInfoPK id;
	@ManyToOne
	private Student student;
	

	@Column(columnDefinition = "tinyint default 1")
	private boolean active;


}
