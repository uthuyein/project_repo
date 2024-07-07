package com.mkt.ym.services.impl;

import static com.mkt.ym.utils.FactoryServices.emf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkt.ym.entity.Student;
import com.mkt.ym.services.StudentService;

import jakarta.persistence.TypedQuery;

public class StudentServiceImpl extends StudentDtoSearchImpl implements StudentService {
	
	
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
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return 0;
	}

	@Override
	public List<Student> search(Student t) {		
		try (var em = emf.createEntityManager()) {
			StringBuilder sb = new StringBuilder("""
					select s from Student s where 1=1
					""");
			Map<String, Object> map = new HashMap<String, Object>();		
			TypedQuery<Student> query = em.createQuery(sb.toString(), Student.class);
			for (Map.Entry<String, Object> m : map.entrySet()) {
				query.setParameter(m.getKey(), m.getValue());
			}
			var list =  query.getResultList();
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}

	@Override
	public int delete(Student t) {
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		var stu = em.find(Student.class, t.getId());
		stu.setActive(false);
		em.getTransaction().commit();
		return 0;
	}


}
