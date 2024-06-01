package com.mkt.ym.services;

import static com.mkt.ym.utils.FactoryServices.emf;

import java.util.List;

import com.mkt.ym.entity.UniversityInfo;

public class UniversityInfoServiceImpl extends UniversityInfoDtoSearch implements UniversityInfoService {

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
