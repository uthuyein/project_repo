package com.mkt.ym.services.impl;

import java.util.List;

import com.mkt.ym.entity.Payment;
import com.mkt.ym.services.PaymentService;

import static com.mkt.ym.utils.FactoryServices.emf;

public class PaymentServiceImpl implements PaymentService {

	@Override
	public void save(Payment payment) {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(payment);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public int update(Payment t) {
		return 0;
	}

	@Override
	public List<Payment> search(Payment t) {
		return null;
	}

	@Override
	public int delete(Payment t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
