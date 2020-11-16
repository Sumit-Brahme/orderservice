package com.sumit.project.orderservice.service;

import com.sumit.project.orderservice.dto.*;
import com.sumit.project.orderservice.entity.*;
import com.sumit.project.orderservice.mapper.OrderMapper;
import com.sumit.project.orderservice.relay.OrderRelay;
import com.sumit.project.orderservice.repository.OrderItemRepository;
import com.sumit.project.orderservice.repository.OrderRepository;
import com.sumit.project.orderservice.utils.CalculationUtils;
import com.sumit.project.orderservice.utils.OrderUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sumit.project.orderservice.dto.StatusDto.*;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger= Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderRelay orderRelay;

    @Autowired
    CalculationUtils calculationUtils;

    @Autowired
    OrderUtils orderUtils;

    public OrderResponseDtoList getOrders() {
        OrderResponseDtoList orderResponseDtoList = new OrderResponseDtoList();
        ResponseStatus responseStatus=new ResponseStatus();
        List<Orders> orderList =orderRepository.findAll();
        if(!CollectionUtils.isEmpty(orderList)) {
            List<OrderResponseDto> orderResponseDtos = orderMapper.mapOrderListToOrderResponseDtoList(orderList);
            responseStatus.setStatus(ORDER_GET_SUCCESS.statusMessage);
            orderResponseDtoList.setOrderResponseDto(orderResponseDtos);
            orderResponseDtoList.setResponseStatus(responseStatus);
        }else{
            responseStatus.setStatus(ORDER_GET_ERROR.statusMessage);
            orderResponseDtoList.setResponseStatus(responseStatus);
        }
        return orderResponseDtoList;
    }

    public OrderResponseDto createOrders(OrderRequestDto orderRequestDto) throws Exception {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        ResponseStatus responseStatus=new ResponseStatus();
        if(OrderStatus.valueOf(orderRequestDto.getOperation())!=null) {
            List<Product> verifiedProductList = new  ArrayList<>();
            Orders order =null;
            if(orderRequestDto.getOperation().equals(OrderStatus.PLACED.name()))
            //try {
                verifiedProductList = orderRelay.buyProducts(orderRequestDto.getProductList());
            //} catch (Exception e) {
                /*logger.error("Products verification failed");
                responseStatus.setStatus(ORDER_CREATE_ERROR.statusMessage);
                responseStatus.setStatusMessage("Products verification failed");
                orderResponseDto.setResponseStatus(responseStatus);
                return orderResponseDto;*/
            //}
            logger.debug("verifiedProductList is " + verifiedProductList);
            if (!CollectionUtils.isEmpty(verifiedProductList)) {
                List<OrderItem> orderItemList = new ArrayList<>();
                verifiedProductList.forEach(product -> {
                    OrderItem orderItem = orderUtils.generateOrderItem(product);
                    orderItemList.add(orderItem);
                    logger.debug("OrderItem added" + orderItem);
                });
                order = orderMapper.mapOrderRequestDtoToOrder(orderRequestDto);
                order.setOrderItemList(orderItemList);
                Double orderPrice=calculationUtils.calculatePrice(orderItemList);
                order.setPrice(orderPrice);
                order.setOrderDate(new Date());
                order.setStatus(OrderStatus.PLACED);
                order = orderRepository.save(order);
                logger.debug("Order saved: " + order.getId());
                Orders finalOrder = order;
                orderItemList.forEach(orderItem -> {
                    orderItem.setOrders(finalOrder);
                });
                orderItemRepository.saveAll(orderItemList);
            }else{
                logger.error("Products verification failed");
                responseStatus.setStatus(ORDER_CREATE_ERROR.statusMessage);
                responseStatus.setStatusMessage("Products verification failed");
                orderResponseDto.setResponseStatus(responseStatus);
                return orderResponseDto;
            }
            orderResponseDto = orderMapper.mapOrderToOrderResponseDto(order);
            responseStatus.setStatus(ORDER_CREATE_SUCCESS.statusMessage);
            orderResponseDto.setResponseStatus(responseStatus);
        }
        else {
            logger.error("Invalid Operation on Order.");
            responseStatus.setStatus(ORDER_CREATE_ERROR.statusMessage);
            responseStatus.setStatusMessage("Invalid Operation on Order.");
            orderResponseDto.setResponseStatus(responseStatus);
        }
        return orderResponseDto;
    }
}
