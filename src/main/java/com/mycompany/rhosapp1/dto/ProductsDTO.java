package com.mycompany.rhosapp1.dto;

import java.math.BigDecimal;

public class ProductsDTO 
{
	private Integer id;
	private String supplierIds;  
    private String productCode;   
    private String productName;   
    private String description;   
    private BigDecimal standardCost;   
    private BigDecimal listPrice;    
    private Integer reorderLevel;   
    private Integer targetLevel;
    private String quantityPerUnit;    
    private boolean discontinued;   
    private Integer minimumReorderQuantity;    
    private String category;
    
	public ProductsDTO() {
		super();
	}

	public ProductsDTO(Integer id, String supplierIds, String productCode, String productName, String description,
			BigDecimal standardCost, BigDecimal listPrice, Integer reorderLevel, Integer targetLevel,
			String quantityPerUnit, boolean discontinued, Integer minimumReorderQuantity, String category) {
		super();
		this.id = id;
		this.supplierIds = supplierIds;
		this.productCode = productCode;
		this.productName = productName;
		this.description = description;
		this.standardCost = standardCost;
		this.listPrice = listPrice;
		this.reorderLevel = reorderLevel;
		this.targetLevel = targetLevel;
		this.quantityPerUnit = quantityPerUnit;
		this.discontinued = discontinued;
		this.minimumReorderQuantity = minimumReorderQuantity;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupplierIds() {
		return supplierIds;
	}

	public void setSupplierIds(String supplierIds) {
		this.supplierIds = supplierIds;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getStandardCost() {
		return standardCost;
	}

	public void setStandardCost(BigDecimal standardCost) {
		this.standardCost = standardCost;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public Integer getTargetLevel() {
		return targetLevel;
	}

	public void setTargetLevel(Integer targetLevel) {
		this.targetLevel = targetLevel;
	}

	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	public Integer getMinimumReorderQuantity() {
		return minimumReorderQuantity;
	}

	public void setMinimumReorderQuantity(Integer minimumReorderQuantity) {
		this.minimumReorderQuantity = minimumReorderQuantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
