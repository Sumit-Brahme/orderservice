package com.sumit.project.orderservice.dto;

import java.util.List;

public class OrderResponseDtoList {
    private List<OrderResponseDto> orderResponseDto;
    private ResponseStatus responseStatus;

    public List<OrderResponseDto> getOrderResponseDto() {
        return orderResponseDto;
    }

    public void setOrderResponseDto(List<OrderResponseDto> orderResponseDto) {
        this.orderResponseDto = orderResponseDto;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
