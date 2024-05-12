package com.mkt.ym.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "parent_tbl")
public class Parent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String motherName;
	private String fatherName;
	private String motherNrc;
	private String fatherNrc;
	
	public Parent(String motherName, String fatherName, String motherNrc, String fatherNrc) {
		super();
		this.motherName = motherName;
		this.fatherName = fatherName;
		this.motherNrc = motherNrc;
		this.fatherNrc = fatherNrc;
	}
	
	
}
