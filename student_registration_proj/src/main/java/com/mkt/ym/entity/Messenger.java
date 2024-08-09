package com.mkt.ym.entity;

import com.mkt.ym.controller.listener.EnableTimesListener;
import com.mkt.ym.controller.listener.Times;
import com.mkt.ym.entity.type.MessageType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "messenger_tbl")
@NoArgsConstructor
public class Messenger implements EnableTimesListener {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private MessageType message;
	private Times times;
	private String text;
	@ManyToOne
	private Payment payment;
	@ManyToOne
	private Student student;
	@ManyToOne
	private Account account;
	@ManyToOne
	private UniversityInfo uniInfo;
	
	public Messenger(Student student) {
		this.student = student;
	}
	
	public Messenger(Integer id) {
		this.id = id;
	}
}
