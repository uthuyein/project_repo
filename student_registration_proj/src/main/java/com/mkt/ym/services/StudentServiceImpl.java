package com.mkt.ym.services;

import static com.mkt.ym.utils.FactoryServices.emf;

import java.util.List;

import com.mkt.ym.entity.Student;

public class StudentServiceImpl implements StudentService {
	
	
	@Override
	public void save(Student t) {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public int update(Student t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Student> search(Student t) {
		return null;
	}

}
