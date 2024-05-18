package com.techm.service.impl;

import com.techm.entity.Biller;
import com.techm.entity.Customer;
import com.techm.entity.Orders;
import com.techm.enums.Role;
import com.techm.repository.BillerRepository;
import com.techm.repository.CustomerRepository;
import com.techm.repository.OrderRepository;
import com.techm.service.CustomerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BillerRepository billerRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Customer registerCustomer(Customer customer) {
        String email = customer.getEmail();
        if (customerRepository.findByEmail(email) != null || billerRepository.findByEmail(email) != null) {
            return null;
        }
        customer.setActive(true);
        customer.setRole(Role.CUSTOMER);
        return customerRepository.save(customer);
    }

    @Override
    public Customer loginCustomer(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

	@Override
	public List<Biller> registeredBillers(Long customerId) {
		List<Orders> orders = orderRepository.findAllByCustomer(getCustomerById(customerId));
		Set<Biller> billers = new HashSet<>();
		for(Orders order : orders) {
			billers.add(order.getProduct().getBiller());
		}
		return billers.stream().collect(Collectors.toList());
	}
}
