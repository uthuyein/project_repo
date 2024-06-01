package com.mkt.ym.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parent_tbl")
@NoArgsConstructor
public class Parent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String motherName;
	private String fatherName;
	private String motherNrc;
	private String fatherNrc;
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean active;
	
	public Parent(String motherName, String fatherName, String motherNrc, String fatherNrc) {
		super();
		this.motherName = motherName;
		this.fatherName = fatherName;
		this.motherNrc = motherNrc;
		this.fatherNrc = fatherNrc;
	}
	
	
}
