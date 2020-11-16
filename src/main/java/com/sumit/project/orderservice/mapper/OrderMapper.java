package com.sumit.project.orderservice.mapper;

import com.sumit.project.orderservice.entity.Orders;
import com.sumit.project.orderservice.dto.OrderRequestDto;
import com.sumit.project.orderservice.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    @Autowired
    ObjectMapperUtils objectMapperUtils;

    public Orders mapOrderRequestDtoToOrder(OrderRequestDto orderRequestDto){
        return objectMapperUtils.map(orderRequestDto, Orders.class);
    }

    public OrderResponseDto mapOrderToOrderResponseDto(Orders order){
        return objectMapperUtils.map(order,OrderResponseDto.class);
    }

    public List<OrderResponseDto> mapOrderListToOrderResponseDtoList(List<Orders> orderList){
        return objectMapperUtils.mapAll(orderList,OrderResponseDto.class);
    }
}
