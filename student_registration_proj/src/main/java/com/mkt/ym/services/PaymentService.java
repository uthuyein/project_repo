package com.mkt.ym.services;

import com.mkt.ym.entity.Payment;
import com.mkt.ym.services.impl.PaymentServiceImpl;

public interface PaymentService extends CommonServices<Payment>{

	public static PaymentService getPaymentService() {
		return new PaymentServiceImpl();
	}
}
