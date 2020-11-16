package com.sumit.project.orderservice.mapper;

import com.sumit.project.orderservice.entity.Product;
import com.sumit.project.orderservice.dto.ProductRequestDto;
import com.sumit.project.orderservice.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {
    @Autowired
    ObjectMapperUtils objectMapperUtils;

    public List<Product> mapProductRequestDtoToProduct(List<ProductRequestDto> productRequestDtoList){
        return objectMapperUtils.mapAll(productRequestDtoList,Product.class);
    }

    public List<ProductResponseDto> mapProductToProductResponseDto(List<Product> productList){
        return objectMapperUtils.mapAll(productList,ProductResponseDto.class);
    }
}