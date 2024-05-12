package com.mkt.ym.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "address_tbl")
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String street;
	private String township;
	private String city;
	
	public Address(String street, String township, String city) {
		super();
		this.street = street;
		this.township = township;
		this.city = city;
	}
	
	
}
