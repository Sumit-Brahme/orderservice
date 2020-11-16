package com.sumit.project.orderservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sumit.project.orderservice.dto.OrderStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue
    private Long id;
    private Date orderDate;
    @Transient
    private String operation;
    private OrderStatus status;
    private Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "orders", fetch = FetchType.EAGER)
    private List<OrderItem> orderItemList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
