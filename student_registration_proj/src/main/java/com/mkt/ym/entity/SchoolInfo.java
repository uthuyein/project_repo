package com.mkt.ym.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "school_info_tbl")
@SecondaryTable(name = "subject_mark_tbl")
@NoArgsConstructor
public class SchoolInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String rollNum;
	private Integer totalMarks;
	@Column(columnDefinition = "boolean default true")
	private boolean active;
	
//	@Column(table = "subject_mark_tbl")
//	private int maths;
//	@Column(table = "subject_mark_tbl")
//	private int Phys;
//	@Column(table = "subject_mark_tbl")
//	private int chems;
//	@Column(table = "subject_mark_tbl")
//	private int eng;
	
	public SchoolInfo(String rollNum, int totalMarks) {
		super();
		this.rollNum = rollNum;
		this.totalMarks = totalMarks;
		
	}
	
}
