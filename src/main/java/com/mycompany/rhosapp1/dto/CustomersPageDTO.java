package com.mycompany.rhosapp1.dto;

import java.util.List;

public class CustomersPageDTO {
	
	private List<CustomersDTO> productsPage;
	private int total;
	
	public CustomersPageDTO() {
		super();
	}

	public CustomersPageDTO(List<CustomersDTO> productsPage, int total) {
		super();
		this.productsPage = productsPage;
		this.total = total;
	}

	public List<CustomersDTO> getProductsPage() {
		return productsPage;
	}

	public void setProductsPage(List<CustomersDTO> productsPage) {
		this.productsPage = productsPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
