package com.mkt.ym.entity;

import java.util.ArrayList;
import java.util.List;

import com.mkt.ym.controller.listener.EnableTimesListener;
import com.mkt.ym.controller.listener.Times;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payment_tbl")
public class Payment implements EnableTimesListener {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private PaymentPk id;
	private String transferFrom;
	private String transactionNum;
	private Integer amount;
	private String note;
	private Times times;
	private Boolean status;
	@OneToMany(mappedBy = "payment",orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Messenger> messengers = new ArrayList<Messenger>();
	@OneToOne
	private UniversityInfo uniInfo;
	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean active = true;

	public void addMessage(Messenger message) {	
		message.setPayment(this);
		messengers.add(message);
	}

}
