package com.mkt.ym.services.impl;

import static com.mkt.ym.utils.FactoryServices.emf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkt.ym.entity.Messenger;
import com.mkt.ym.entity.Payment;
import com.mkt.ym.services.MessengerService;
import com.mkt.ym.services.PaymentService;

import jakarta.persistence.TypedQuery;

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
		var messService = MessengerService.getMessengerService();
		for (Messenger m : t.getMessengers()) {
			messService.save(m);
		}

		var em = emf.createEntityManager();
		em.getTransaction().begin();
		var query = em.createQuery("""
				update Payment p set p.status = :status
				 where p.id.paymentType = :type and p.id.transactionDate = :date and p.id.transactionTime = :time
				""");
		query.setParameter("status", t.getStatus());
		query.setParameter("type", t.getId().getPaymentType());
		query.setParameter("date", t.getId().getTransactionDate());
		query.setParameter("time", t.getId().getTransactionTime());

		var res = query.executeUpdate();
		em.getTransaction().commit();
		return res;

	}

	@Override
	public List<Payment> search(Payment t) {
		var em = emf.createEntityManager();
		Map<String, Object> map = new HashMap<String, Object>();
		var sb = new StringBuilder("select p from Payment p join p.uniInfo  u join u.student s where p.active = true");
		
		if (null != t) {
			if (null != t.getStatus()) {
				sb.append(" and p.status = :status");
				map.put("status", t.getStatus());
			} else {
				sb.append(" and p.status is null");
			}
			if (null != t.getTransferFrom() && !t.getTransferFrom().isEmpty()) {
			}
			if (null != t.getTransactionNum() && !t.getTransactionNum().isEmpty()) {
			}

			if (null != t.getId()) {
				if (null != t.getId().getPaymentType()) {
					sb.append(" and p.id.paymentType = :type");
					map.put("type", t.getId().getPaymentType());
				}
			}

			if (null != t.getUniInfo()) {
				if (null != t.getUniInfo().getStudent() && null != t.getUniInfo().getStudent().getName()) {
					sb.append(" and p.uniInfo.student.name = :name");
					map.put("name", t.getUniInfo().getStudent().getName());
				}
				if (null != t.getUniInfo().getId().getOpenYear()) {
					sb.append(" and p.uniInfo.id.openYear = :year");
					map.put("year", t.getUniInfo().getId().getOpenYear());
				}
				if (null != t.getUniInfo().getId().getMajor()) {
					sb.append(" and p.uniInfo.id.major = :major");
					map.put("major", t.getUniInfo().getId().getMajor());
				}
				if (null != t.getUniInfo().getId().getUniYear()) {
					sb.append(" and p.uniInfo.id.uniYear = :uniYear");
					map.put("uniYear", t.getUniInfo().getId().getUniYear());
				}
				if (null != t.getUniInfo().getId().getRollNumber()) {
					sb.append(" and p.uniInfo.id.rollNumber = :roll");
					map.put("roll", t.getUniInfo().getId().getRollNumber());
				}
			}
		}
		
		TypedQuery<Payment> query = em.createQuery(sb.toString(), Payment.class);
		for (Map.Entry<String, Object> m : map.entrySet()) {
			query.setParameter(m.getKey(), m.getValue());
		}
		return query.getResultList();
	}

	@Override
	public int delete(Payment t) {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		var query = em.createQuery(
				"delete from Payment p where p.id.paymentType = :type and p.id.transactionDate = :date and p.id.transactionTime = :time");
		query.setParameter("type", t.getId().getPaymentType());
		query.setParameter("date", t.getId().getTransactionDate());
		query.setParameter("time", t.getId().getTransactionTime());
		int result = query.executeUpdate();
		em.getTransaction().commit();
		return result;

	}

}
