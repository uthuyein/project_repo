package com.mkt.ym.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ExcludeDefaultListeners;
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
@ExcludeDefaultListeners
public class SchoolInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String rollNum;
	private Integer totalMarks;
	@Column(nullable = false,columnDefinition = "boolean default true")
	private boolean active = true;
	
	public SchoolInfo(String rollNum, int totalMarks) {
		super();
		this.rollNum = rollNum;
		this.totalMarks = totalMarks;
		
	}
	
}
