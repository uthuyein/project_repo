package com.mkt.ym.entity;

import com.mkt.ym.entity.type.PaymentType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Payment {

	private int id;
	private PaymentType type;
	private String transactionNum;
	private String transferFrom;
	private int amount;
	
	
}
