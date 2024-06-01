package com.mkt.ym;

import org.junit.jupiter.api.Test;

import com.mkt.ym.entity.Account;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AccountTest extends CommonEntityManager{

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("student_registration");;
	
		
	@Test
	void test() {
		var em = emf.createEntityManager();
		var query = em.createQuery("select a from Account a ", Account.class);

		
		//em.close();
		var s = query.getResultList();
		System.out.println(s.get(0).getLoginId()+"\t"+s.get(0).getPassword());
	}
}
