package com.mycompany.rhosapp1.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mycompany.rhosapp1.dao.CustomersDAO;
import com.mycompany.rhosapp1.dto.CustomersDTO;
import com.mycompany.rhosapp1.dto.CustomersPageDTO;
import com.mycompany.rhosapp1.entity.Customers;
import com.mycompany.rhosapp1.service.CustomersService;

@Stateless
public class CustomersServiceImpl implements CustomersService{
	
	CustomersDAO dao;
	
	public CustomersServiceImpl()
	{
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("RHOSAPP1PU");
		this.dao = new CustomersDAO(null);
	}
	
	public Integer count()
	{
		return this.dao.getCustomersCount();
	}
	
	public List<CustomersDTO> findAll()
	{
		List<CustomersDTO> ret = new ArrayList<CustomersDTO>();	
		for(Customers c : dao.findCustomersEntities())
		{
			ret.add(new CustomersDTO(c.getId(), c.getCompany(), c.getLastName(), c.getFirstName(), c.getEmailAddress(), c.getJobTitle(), c.getBusinessPhone(), c.getHomePhone(), c.getMobilePhone(), c.getFaxNumber(), c.getAddress(), c.getCity(), c.getStateProvince(), c.getZipPostalCode(), c.getCountryRegion(), c.getWebPage(), c.getNotes()));
		}	
		return ret;
	}
	
	public CustomersPageDTO findPage(Integer pageNum)
	{
		CustomersPageDTO ret;
		
		List<CustomersDTO> customersDTOList = new ArrayList<CustomersDTO>();	
		CustomersDTO customersDTO;
		for(Customers c : dao.findCustomersEntities(10, pageNum * 10))
		{
			customersDTO = new CustomersDTO(c.getId(), c.getCompany(), c.getLastName(), c.getFirstName(), c.getEmailAddress(), c.getJobTitle(), c.getBusinessPhone(), c.getHomePhone(), c.getMobilePhone(), c.getFaxNumber(), c.getAddress(), c.getCity(), c.getStateProvince(), c.getZipPostalCode(), c.getCountryRegion(), c.getWebPage(), c.getNotes());
			customersDTOList.add(customersDTO);
		}		
		ret = new CustomersPageDTO(customersDTOList, dao.getCustomersCount());
	
		return ret;
	}
	

}
