package com.techm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techm.entity.Biller;
import com.techm.entity.Customer;

@Service
public interface CustomerService {

	public Customer registerCustomer(Customer customer);
	public Customer loginCustomer(String email, String password);
	public Customer getCustomerById(Long customerId);
	public Customer updateCustomer(Customer customer);
	public List<Biller> registeredBillers(Long customerId);
}
