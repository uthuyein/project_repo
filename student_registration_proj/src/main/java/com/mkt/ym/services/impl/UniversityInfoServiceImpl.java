package com.mkt.ym.services.impl;

import static com.mkt.ym.utils.FactoryServices.emf;

import java.util.List;

import com.mkt.ym.entity.UniversityInfo;
import com.mkt.ym.services.UniversityInfoService;

public class UniversityInfoServiceImpl extends UniversityInfoDtoSearchImpl implements UniversityInfoService {

	@Override
	public void save(UniversityInfo t) {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	@Override
	public int update(UniversityInfo t) {
		return 0;
	}

	@Override
	public List<UniversityInfo> search(UniversityInfo t) {
		return null;
	}

	

}
