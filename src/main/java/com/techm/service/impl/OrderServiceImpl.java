package com.techm.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.entity.Customer;
import com.techm.entity.Orders;
import com.techm.entity.Product;
import com.techm.repository.OrderRepository;
import com.techm.service.CustomerService;
import com.techm.service.OrderService;
import com.techm.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Override
	public Orders buyProduct(Long customerId, Long productId) {
		Customer customer = customerService.getCustomerById(customerId);
		Product product = productService.getProductById(productId);

		Orders order = new Orders();
		order.setCustomer(customer);
		order.setProduct(product);
		order.setOrderDate(LocalDateTime.now());
		order.setOrderStatus("Pending");
		order.setPaymentStatus("Paid");
		int random = 100000 + (int)(Math.random() * ((999999-100000) + 1));
		String billId = "PS" + String.valueOf(random);
		order.setBillId(billId);

		return orderRepository.save(order);
	}

	@Override
	public List<Orders> allOrdersByBiller(Long billerId) {
		List<Orders> allOrders = orderRepository.findAll();
		List<Orders> billerOrders = new ArrayList<Orders>();
		for(Orders orders : allOrders) {
			if(orders.getProduct().getBiller().getBillerId() == billerId) {
				billerOrders.add(orders);
			}
		}
		return billerOrders;
	}

	@Override
	public List<Orders> pendingOrdersByBiller(Long billerId) {
		List<Orders> billerOrders = allOrdersByBiller(billerId);
		List<Orders> pendingOrders = new ArrayList<Orders>();
		for(Orders orders : billerOrders) {
			if(orders.getOrderStatus().equals("Pending")) {
				pendingOrders.add(orders);
			}
		}
		return pendingOrders;
	}

	@Override
	public boolean acceptOrder(Long orderId) {
		Orders order = orderRepository.findById(orderId).orElse(null);
		if(order != null) {
			order.setOrderStatus("Approved");
			orderRepository.save(order);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean rejectOrder(Long orderId) {
		Orders order = orderRepository.findById(orderId).orElse(null);
		if(order != null) {
			order.setOrderStatus("Rejected");
			order.setPaymentStatus("Refund");
			orderRepository.save(order);
			return true;
		}
		return false;
	}

	@Override
	public Orders getOrderById(Long orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}

	@Override
	public List<Orders> getOrderByCustomer(Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		List<Orders> orders = orderRepository.findAllByCustomer(customer);
		return orders;
	}

	@Override
	public List<Orders> getOrdersByCustomerAndStatus(Long customerId, String status) {
		List<Orders> allOrders = getOrderByCustomer(customerId);
		List<Orders> orders = new ArrayList<>();
		for(Orders order : allOrders) {
			if(order.getOrderStatus().equals(status))
				orders.add(order);
		}
		return orders;
	}

}
