package com.mycompany.rhosapp1.dto;

import java.util.List;

public class CustomersPageDTO {
	
	private List<CustomersDTO> customersPage;
	private int total;
	
	public CustomersPageDTO() {
		super();
	}

	public CustomersPageDTO(List<CustomersDTO> productsPage, int total) {
		super();
		this.customersPage = productsPage;
		this.total = total;
	}

	public List<CustomersDTO> getCustomersPage() {
		return customersPage;
	}

	public void setCustomersPage(List<CustomersDTO> customersPage) {
		this.customersPage = customersPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}	
}
