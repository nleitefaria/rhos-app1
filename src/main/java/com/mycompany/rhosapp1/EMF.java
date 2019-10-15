package com.mycompany.rhosapp1;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF {
	private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("RHOSAPP1PU");

	private EMF() {
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
}