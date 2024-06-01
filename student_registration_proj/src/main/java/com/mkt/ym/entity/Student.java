package com.mkt.ym.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student_tbl")
@SecondaryTable(name = "contact_tbl")
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, length = 45)
	private String name;
	@Column(nullable = false)
	private LocalDate dob;
	private String image;
	@Column(nullable = false)
	private String nrc;
	private String religion;

	@Column(table = "contact_tbl")
	private String email;
	@Column(table = "contact_tbl")
	private String primaryContact;
	@Column(table = "contact_tbl")
	private String secondaryContact;

	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private SchoolInfo schoolInfo;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Parent parent;
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Address address;
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean active;


	public Student(String name, LocalDate dob,String religion, String image, String nrc, String email, String primaryContact,
			String secondaryContact) {
		super();
		this.name = name;
		this.dob = dob;
		this.religion = religion;
		this.image = image;
		this.nrc = nrc;
		this.email = email;
		this.primaryContact = primaryContact;
		this.secondaryContact = secondaryContact;
	}

}
