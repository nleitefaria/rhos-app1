package com.mycompany.rhosapp1.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mycompany.rhosapp1.dao.ProductsDAO;
import com.mycompany.rhosapp1.dto.ProductsDTO;
import com.mycompany.rhosapp1.dto.ProductsPageDTO;
import com.mycompany.rhosapp1.entity.Products;
import com.mycompany.rhosapp1.service.ProductsService;

@Stateless
public class ProductsServiceImpl implements ProductsService {
	
	ProductsDAO dao;
	
	public ProductsServiceImpl()
	{
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("RHOSAPP1PU");
		this.dao = new ProductsDAO(null);
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
	
	public ProductsPageDTO findPage(Integer pageNum)
	{
		ProductsPageDTO ret;
		
		List<ProductsDTO> productsDTOList = new ArrayList<ProductsDTO>();
		ProductsDTO productsDTO;
		for(Products p : dao.findProductsEntities(10, pageNum * 10))
		{
			productsDTO = new ProductsDTO(p.getId(), p.getSupplierIds(), p.getProductCode(), p.getProductName(), p.getDescription(), p.getStandardCost(), p.getListPrice(), p.getReorderLevel(), p.getTargetLevel(), p.getQuantityPerUnit(), p.getDiscontinued(), p.getMinimumReorderQuantity(), p.getCategory());
			productsDTOList.add(productsDTO);
		}		
		ret = new ProductsPageDTO(productsDTOList, dao.getProductsCount());
	
		return ret;
	}

}
