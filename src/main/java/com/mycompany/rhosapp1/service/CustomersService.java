package com.mycompany.rhosapp1.service;

import java.util.List;

import com.mycompany.rhosapp1.dto.CustomersDTO;
import com.mycompany.rhosapp1.dto.CustomersPageDTO;

public interface CustomersService {
	
	Integer count();
	List<CustomersDTO> findAll();
	CustomersPageDTO findPage(Integer pageNum);

}
