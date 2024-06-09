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
@Table(name = "address_tbl")
@NoArgsConstructor

public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String street;
	private String township;
	private String city;
	private String hostelName;
	@Column(columnDefinition = "tinyint default 1")
	private boolean active;
	
	public Address(String street, String township, String city) {
		super();
		this.street = street;
		this.township = township;
		this.city = city;
	}
	
	
}
