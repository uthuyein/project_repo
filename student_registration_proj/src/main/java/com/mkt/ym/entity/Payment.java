package com.mkt.ym.entity;

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
public class Payment {

	@EmbeddedId
	private RegisterPk id;
	private String transferFrom;
	private String transactionNum;	
	private Integer amount;
	private String note;
	
	@ManyToOne
	private UniversityInfo uniInfo;
	private boolean active;
	
}
