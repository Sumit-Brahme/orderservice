package com.sumit.project.orderservice.dto;

public enum StatusDto {
    ORDER_GET_SUCCESS("Orders Fetched Successfully."),
    ORDER_GET_ERROR("Error Occurred While Fetching Orders."),
    ORDER_CREATE_SUCCESS("Order Placed Successfully."),
    ORDER_CREATE_ERROR("Error Occurred While Placing the order."),
    PRODUCT_BUY_SUCCESS("Products Buying Successful.");
    public final String statusMessage;

    StatusDto(String statusMessage) {
        this.statusMessage=statusMessage;
    }
}
