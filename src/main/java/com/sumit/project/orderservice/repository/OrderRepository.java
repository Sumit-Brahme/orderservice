package com.sumit.project.orderservice.repository;

import com.sumit.project.orderservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
