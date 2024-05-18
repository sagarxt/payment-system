package com.techm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techm.entity.Orders;

@Service
public interface OrderService {

	Orders buyProduct(Long customerId, Long productId);

	List<Orders> pendingOrdersByBiller(Long billerId);

	List<Orders> allOrdersByBiller(Long billerId);

	boolean acceptOrder(Long orderId);

	boolean rejectOrder(Long orderId);

	Orders getOrderById(Long orderId);

	List<Orders> getOrderByCustomer(Long customerId);

	List<Orders> getOrdersByCustomerAndStatus(Long customerId, String status);
}
