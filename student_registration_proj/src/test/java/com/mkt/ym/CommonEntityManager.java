package com.mkt.ym;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CommonEntityManager {

	static EntityManagerFactory emf;
	EntityManager em;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("student_registration");
	}
	
	@AfterAll
	static void closeEmf() {
		if( null != emf || emf.isOpen())
			emf.close();
	}
	
	@BeforeEach 
	void createEm() {
		em = emf.createEntityManager();
	}
	
	@AfterEach
	void closeEm() {
		if(null != em || em.isOpen())
			em.close();
	}
	
	
}
