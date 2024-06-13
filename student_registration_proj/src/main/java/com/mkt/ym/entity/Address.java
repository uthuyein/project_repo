package com.mkt.ym.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ExcludeDefaultListeners;
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
@ExcludeDefaultListeners
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String street;
	private String township;
	private String city;
	private String hostelName;
	@Column(columnDefinition = "boolean not null default true")
	private boolean active;
	
	public Address(String street, String township, String city) {
		super();
		this.street = street;
		this.township = township;
		this.city = city;
	}
	
	
}
