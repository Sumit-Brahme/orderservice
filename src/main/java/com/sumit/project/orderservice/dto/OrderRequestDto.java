package com.sumit.project.orderservice.dto;

import java.util.List;

public class OrderRequestDto {
    private String operation;
    private List<ProductRequestDto> productList;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<ProductRequestDto> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductRequestDto> productList) {
        this.productList = productList;
    }
}
