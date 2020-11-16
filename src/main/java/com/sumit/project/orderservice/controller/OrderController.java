package com.sumit.project.orderservice.controller;

import com.sumit.project.orderservice.dto.OrderRequestDto;
import com.sumit.project.orderservice.dto.OrderResponseDto;
import com.sumit.project.orderservice.dto.OrderResponseDtoList;
import com.sumit.project.orderservice.dto.ResponseStatus;
import com.sumit.project.orderservice.service.OrderService;


import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static com.sumit.project.orderservice.dto.StatusDto.ORDER_CREATE_ERROR;
import static com.sumit.project.orderservice.dto.StatusDto.ORDER_GET_ERROR;


@Controller
public class OrderController {
    private static final Logger logger= Logger.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    //API to get a single order details
    @GetMapping("order/get")
    @ResponseBody
    @ApiOperation(value="This API is used to get All Orders.")
    public ResponseEntity getOrders(){
        OrderResponseDtoList orderResponseDtoList =new OrderResponseDtoList();
        ResponseStatus responseStatus=new ResponseStatus();
        logger.debug("Inside getOrders()");
        try {
            orderResponseDtoList = orderService.getOrders();
            return new ResponseEntity(orderResponseDtoList,HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error occurred in getOrder()");
            responseStatus.setStatus(ORDER_GET_ERROR.statusMessage);
            orderResponseDtoList.setResponseStatus(responseStatus);
            return new ResponseEntity(orderResponseDtoList,HttpStatus.OK);
        }
    }

    @PostMapping("order/create")
    @ResponseBody
    @ApiOperation(value="This API is used to Create an Order.")
    public ResponseEntity createOrders(@RequestBody OrderRequestDto orderRequestDto){
        logger.debug("Inside createOrders()");
        OrderResponseDto orderResponseDto=null;
        ResponseStatus responseStatus=new ResponseStatus();
        try {
            if(orderRequestDto!=null){
                orderResponseDto = orderService.createOrders(orderRequestDto);
            }else{
                responseStatus.setStatus(ORDER_CREATE_ERROR.statusMessage);
            }
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            logger.error("Error occurred while creating order.");
            responseStatus.setStatus(ORDER_CREATE_ERROR.statusMessage);
            responseStatus.setStatusMessage("Error occurred while creating order.");
            orderResponseDto.setResponseStatus(responseStatus);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        }
    }


}
