package com.techm.service.impl;

import com.techm.entity.Biller;
import com.techm.entity.BillerDashDTO;
import com.techm.entity.Customer;
import com.techm.entity.Orders;
import com.techm.enums.Role;
import com.techm.repository.BillerRepository;
import com.techm.repository.CustomerRepository;
import com.techm.service.BillerService;
import com.techm.service.OrderService;
import com.techm.service.ProductService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillerServiceImpl implements BillerService {

    @Autowired
    private BillerRepository billerRepository;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderService orderService;

    @Override
    public Biller registerBiller(Biller biller) {
        String email = biller.getEmail();
        if (billerRepository.findByEmail(email) != null || customerRepository.findByEmail(email) != null) {
            return null;
        }
        biller.setRole(Role.BILLER);
        biller.setActive(true);
        return billerRepository.save(biller);
    }

    @Override
    public Biller loginBiller(String email, String password) {
        return billerRepository.findByEmailAndPassword(email, password);
    }

	@Override
	public Biller getBillerById(Long billerId) {
		return billerRepository.findById(billerId).orElse(null);
	}

	@Override
	public Biller updateBiller(Biller biller) {
		return billerRepository.save(biller);
	}

	@Override
	public List<Biller> getAllBillers() {
		return billerRepository.findAll();
	}

	@Override
	public BillerDashDTO billerMoreInfo(Long billerId) {
		Biller biller = getBillerById(billerId);
		int totalProducts = productService.getAllProductsByBiller(biller).size();
		
		int totalOrders = orderService.allOrdersByBiller(billerId).size();
		
		int pendingOrders = orderService.pendingOrdersByBiller(billerId).size();
		
		BillerDashDTO bdd = new BillerDashDTO();
		bdd.setPendingOrders(pendingOrders);
		bdd.setTotalProducts(totalProducts);
		bdd.setTotalOrders(totalOrders);
		
		return bdd;
	}
	
	@Override
	public List<Customer> registeredCustomers(Long billerId) {
		List<Orders> orders = orderService.allOrdersByBiller(billerId);
		Set<Customer> customers = new HashSet<>();
		for(Orders order : orders) {
			customers.add(order.getCustomer());
		}
		return customers.stream().collect(Collectors.toList());
	}
}
