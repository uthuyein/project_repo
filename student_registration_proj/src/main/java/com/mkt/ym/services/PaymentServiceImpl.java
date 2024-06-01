package com.mkt.ym.services;

import java.util.List;

import com.mkt.ym.entity.Payment;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Payment> search(Payment t) {
		// TODO Auto-generated method stub
		return null;
	}

}
