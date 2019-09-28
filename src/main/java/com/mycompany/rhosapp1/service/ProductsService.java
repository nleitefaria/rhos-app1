package com.mycompany.rhosapp1.service;

import java.util.List;

import com.mycompany.rhosapp1.dto.ProductsDTO;
import com.mycompany.rhosapp1.dto.ProductsPageDTO;

public interface ProductsService {
	
	Integer count();
	List<ProductsDTO> findAll();
	ProductsPageDTO findPage(Integer pageNum);

}
