package com.sumit.project.orderservice.relay;

import com.sumit.project.orderservice.dto.ProductRelayResponse;
import com.sumit.project.orderservice.dto.ProductRequestDto;
import com.sumit.project.orderservice.dto.ProductRequestDtoList;
import com.sumit.project.orderservice.entity.*;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.sumit.project.orderservice.dto.StatusDto.PRODUCT_BUY_SUCCESS;

@Component
public class OrderRelay {

    private static final Logger logger= Logger.getLogger(OrderRelay.class);

    /*public List<Product> buyProducts(List<ProductRequestDto> productRequestDtoList) throws Exception {
        String url="http://localhost:8090/product/buy";
        RestTemplate restTemplate=new RestTemplate();
        List<Product> verifiedProductList=null;
        logger.debug("Calling byProduct(): "+ url);
        ResponseEntity<Product[]> productListResponseEntity = restTemplate.postForEntity(url, productRequestDtoList, Product[].class);
        logger.debug("Got response from byProduct() with status code: "+ productListResponseEntity.getStatusCode());
        if(productListResponseEntity.getStatusCode()== HttpStatus.CREATED) {
            verifiedProductList = Arrays.asList(productListResponseEntity.getBody());
        }
        else{
            logger.error("Error occurred while verifying product.");
            throw new Exception();
        }
        return verifiedProductList;
    }*/

    public List<Product> buyProducts(List<ProductRequestDto> productRequestDtos) throws Exception {
        String url="http://localhost:8090/product/buy";
        RestTemplate restTemplate=new RestTemplate();
        List<Product> verifiedProductList=null;
        ProductRequestDtoList productRequestDtoList=new ProductRequestDtoList();
        productRequestDtoList.setProductRequestDtoList(productRequestDtos);
        logger.debug("Calling byProduct(): "+ url);
        ResponseEntity<ProductRelayResponse> productListResponseEntity = restTemplate.postForEntity(url, productRequestDtoList, ProductRelayResponse.class);
        logger.debug("Got response from byProduct() with status code: "+ productListResponseEntity.getStatusCode());
        if(productListResponseEntity.getStatusCode()== HttpStatus.CREATED) {
            ProductRelayResponse productRelayResponse = productListResponseEntity.getBody();
            if(productRelayResponse.getResponseStatus().getStatus().equals(PRODUCT_BUY_SUCCESS.statusMessage)) {
                verifiedProductList = productRelayResponse.getProductList();
            }
        }
        return verifiedProductList;
    }
}
