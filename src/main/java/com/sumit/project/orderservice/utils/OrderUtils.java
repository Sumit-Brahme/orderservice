package com.sumit.project.orderservice.utils;

import com.sumit.project.orderservice.entity.OrderItem;
import com.sumit.project.orderservice.entity.Product;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class OrderUtils {
    private static final Logger logger= Logger.getLogger(OrderUtils.class);
    public OrderItem generateOrderItem(Product product){
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(product.getPrice());
        orderItem.setQuantity(product.getQuantity());
        orderItem.setProduct(product);
        logger.debug("OrderItem generated: "+orderItem.getId());
        return orderItem;
    }
}
