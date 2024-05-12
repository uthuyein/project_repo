package com.mkt.ym.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "account_tbl")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String loginId;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public enum Role{
		ADMIN,STUDENT;
	}
}
