package com.sumit.project.orderservice.service;

import com.sumit.project.orderservice.dto.OrderRequestDto;
import com.sumit.project.orderservice.dto.OrderResponseDto;
import com.sumit.project.orderservice.dto.OrderResponseDtoList;

public interface OrderService {
    OrderResponseDtoList getOrders();

    OrderResponseDto createOrders(OrderRequestDto orderRequestDto) throws Exception;
}