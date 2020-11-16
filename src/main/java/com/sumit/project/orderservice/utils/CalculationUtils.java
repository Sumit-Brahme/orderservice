package com.sumit.project.orderservice.utils;

import com.sumit.project.orderservice.entity.OrderItem;
import com.sumit.project.orderservice.entity.Orders;
import com.sumit.project.orderservice.entity.Product;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CalculationUtils {
    private static final Logger logger= Logger.getLogger(CalculationUtils.class);
    /*public Orders calculatePrice(Orders order){
        double orderPrice=order.getOrderItemList().stream().mapToDouble(orderItem -> orderItem.getPrice()*orderItem.getQuantity()).sum();
        order.setPrice(orderPrice);
        logger.debug("Total price for the order: "+orderPrice);
        return order;
    }*/

    public Double calculatePrice(List<OrderItem> orderItemList){
        Double orderPrice=orderItemList.stream().mapToDouble(orderItem -> orderItem.getPrice()*orderItem.getQuantity()).sum();
        logger.debug("Total price for the order: "+orderPrice);
        return orderPrice;
    }

}
