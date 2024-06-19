package com.mkt.ym.entity;

import com.mkt.ym.controller.listener.EnableTimesListener;
import com.mkt.ym.controller.listener.Times;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payment_tbl")
public class Payment implements EnableTimesListener{

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private PaymentPk id;
	private String transferFrom;
	private String transactionNum;	
	private Integer amount;
	private String note;
	private Times times;
	@ManyToOne
	private UniversityInfo uniInfo;
	@Column(nullable = false,columnDefinition = "boolean default true")
	private boolean active  = true;
	
}
