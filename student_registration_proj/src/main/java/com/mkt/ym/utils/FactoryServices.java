package com.mkt.ym.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class FactoryServices {
	public static EntityManagerFactory emf;

	static {
		emf = Persistence.createEntityManagerFactory("student_registration");
	}

	public static void closeEmf() {
		if (null != emf || emf.isOpen())
			emf.close();
	}
}
