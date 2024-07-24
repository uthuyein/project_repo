package com.mkt.ym.services.impl;

import static com.mkt.ym.utils.FactoryServices.emf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkt.ym.entity.Messenger;
import com.mkt.ym.services.MessengerService;

import jakarta.persistence.TypedQuery;

public class MessengerServiceImpl implements MessengerService {

	@Override
	public void save(Messenger m) {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();
	}

	@Override
	public int update(Messenger m) {
		return 0;
	}

	@Override
	public int delete(Messenger m) {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		var query = em.createQuery("delete from Messenger m where m.id = :id");
		query.setParameter("id", m.getId());
		query.executeUpdate();
		em.getTransaction().commit();
		return 0;
	}

	@Override
	public List<Messenger> search(Messenger m) {
		var em = emf.createEntityManager();
		Map<String, Object> temp = new HashMap<String, Object>();
		var sb = new StringBuilder("""
				select m from Messenger m where 1=1
				""");
		if (null != m) {
			if (null != m.getStudent()) {
				if (m.getStudent().getId() > 0) {
					sb.append(" and m.student.id = :sId");
					temp.put("sId", m.getStudent().getId());
				}
			}
		}
		TypedQuery<Messenger> query = em.createQuery(sb.toString(), Messenger.class);
		for (Map.Entry<String, Object> map : temp.entrySet()) {
			query.setParameter(map.getKey(), map.getValue());
		}
		return query.getResultList();

	}

}
