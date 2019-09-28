package com.mycompany.rhosapp1.dto;

import java.util.List;

public class ProductsPageDTO {
	
	private List<ProductsDTO> productsPage;
	private int total;
	
	public ProductsPageDTO() {
		super();
	}

	public ProductsPageDTO(List<ProductsDTO> productsPage, int total) {
		super();
		this.productsPage = productsPage;
		this.total = total;
	}

	public List<ProductsDTO> getProductsPage() {
		return productsPage;
	}

	public void setProductsPage(List<ProductsDTO> productsPage) {
		this.productsPage = productsPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
