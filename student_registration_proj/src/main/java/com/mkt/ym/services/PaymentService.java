package com.mkt.ym.services;

import com.mkt.ym.entity.Payment;
import com.mkt.ym.utils.CommonServices;

public interface PaymentService extends CommonServices<Payment>{

	public static PaymentService getPaymentService() {
		return new PaymentServiceImpl();
	}
}
