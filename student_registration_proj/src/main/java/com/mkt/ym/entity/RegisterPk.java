package com.mkt.ym.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.mkt.ym.entity.type.PaymentType;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RegisterPk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	
	private LocalDate transactionDate;
	private LocalTime transactionTime;
	
	
}
