package com.mycompany.rhosapp1.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mycompany.rhosapp1.dao.CustomersDAO;
import com.mycompany.rhosapp1.dto.CustomersDTO;
import com.mycompany.rhosapp1.entity.Customers;

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
	
	public List<CustomersDTO> findAll()
	{
		List<CustomersDTO> ret = new ArrayList<CustomersDTO>();	
		for(Customers c : customersDAO.findCustomersEntities())
		{
			ret.add(new CustomersDTO(c.getId(), c.getCompany(), c.getLastName(), c.getFirstName(), c.getEmailAddress(), c.getJobTitle(), c.getBusinessPhone(), c.getHomePhone(), c.getMobilePhone(), c.getFaxNumber(), c.getAddress(), c.getCity(), c.getStateProvince(), c.getZipPostalCode(), c.getCountryRegion(), c.getWebPage(), c.getNotes()));
		}	
		return ret;
	}
	
	

}
