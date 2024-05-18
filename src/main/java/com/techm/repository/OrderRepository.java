package com.techm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techm.entity.Customer;
import com.techm.entity.Orders;
import com.techm.entity.Product;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

	Orders findByCustomerAndProduct(Customer customer, Product product);

	List<Orders> findAllByCustomer(Customer customer);

}
