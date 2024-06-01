package com.mkt.ym.services.impl;

import static com.mkt.ym.utils.FactoryServices.emf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkt.ym.entity.Account;
import com.mkt.ym.services.AccountService;

import jakarta.persistence.TypedQuery;

public class AccountServiceImpl implements AccountService {

	@Override
	public void save(Account t) {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();

	}

	@Override
	public int update(Account t) {
		return 0;
	}

	@Override
	public List<Account> search(Account acc) {

		try (var em = emf.createEntityManager()) {

			StringBuilder sb = new StringBuilder("select c from Account c where 1=1");
			Map<String, Object> map = new HashMap<String, Object>();

			if (null != acc) {
				if (null != acc.getLoginId()) {
					sb.append(" and c.loginId = :loginId");
					map.put("loginId", acc.getLoginId());
				}

				if (null != acc.getStudent()) {

				}
			}

			TypedQuery<Account> query = em.createQuery(sb.toString(), Account.class);

			for (Map.Entry<String, Object> m : map.entrySet()) {
				query.setParameter(m.getKey(), m.getValue());
			}
			return query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
