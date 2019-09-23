package com.mycompany.rhosapp1.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mycompany.rhosapp1.dao.CustomersDAO;

@Stateless
public class CustomersService {
	
	CustomersDAO customersDAO;
	
	public CustomersService()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RHOSAPP1PU");
		this.customersDAO = new CustomersDAO(emf);
	}
	
	public Integer count()
	{
		return this.customersDAO.getCustomersCount();
	}
	
	

}
