package com.mycompany.rhosapp1.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mycompany.rhosapp1.dao.ProductsDAO;
import com.mycompany.rhosapp1.dto.ProductsDTO;
import com.mycompany.rhosapp1.entity.Products;

@Stateless
public class ProductsService {
	
	ProductsDAO dao;
	
	public ProductsService()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RHOSAPP1PU");
		this.dao = new ProductsDAO(emf);
	}
	
	public Integer count()
	{
		return this.dao.getProductsCount();
	}
	
	public List<ProductsDTO> findAll()
	{
		List<ProductsDTO> ret = new ArrayList<ProductsDTO>();	
		Products p;
		for(int i = 0 ; i < dao.findProductsEntities().size(); i++)
		{
			p = dao.findProductsEntities().get(i);
			ret.add(new ProductsDTO(p.getId(), p.getSupplierIds(), p.getProductCode(), p.getProductName(), p.getDescription(), p.getStandardCost(), p.getListPrice(), p.getReorderLevel(), p.getTargetLevel(), p.getQuantityPerUnit(), p.getDiscontinued(), p.getMinimumReorderQuantity(), p.getCategory()));
		}		
		return ret;
	}

}
