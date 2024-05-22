package com.mkt.ym.entity;

import com.mkt.ym.entity.type.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account_tbl")
@NoArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String loginId;
	private String password;
	
	@OneToOne
	private Student student;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public Account(String loginId) {
		this.loginId = loginId;
		
	}
}
