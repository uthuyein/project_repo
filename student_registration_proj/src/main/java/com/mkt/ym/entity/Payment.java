package com.mkt.ym.entity;

import com.mkt.ym.controller.listener_filter.EnableTimesListener;
import com.mkt.ym.controller.listener_filter.Times;

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
	@Column(columnDefinition = "boolean not null default true")
	private boolean active;
	
}
