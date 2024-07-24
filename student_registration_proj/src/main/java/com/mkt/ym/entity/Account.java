package com.mkt.ym.entity;

import java.util.ArrayList;
import java.util.List;

import com.mkt.ym.controller.listener.EnableTimesListener;
import com.mkt.ym.controller.listener.Times;
import com.mkt.ym.entity.type.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Account implements EnableTimesListener {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String loginId;
	private String password;
	private Times times;
	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean active = true;
	@OneToOne
	private UniversityInfo uniInfo;
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "account",orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Messenger> messengers = new ArrayList<Messenger>();
	
	public Account(String username, String loginId) {
		this.username = username;
		this.loginId = loginId;

	}

	public Account(String loginId) {
		this.loginId = loginId;

	}
	
	public void addMessage(Messenger message) {	
		message.setAccount(this);
		messengers.add(message);
	}
}
