package com.techm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techm.entity.Biller;
import com.techm.entity.BillerDashDTO;
import com.techm.entity.Customer;

@Service
public interface BillerService {

	public Biller registerBiller(Biller biller);
	public Biller loginBiller(String email, String password);
	public Biller getBillerById(Long billerId);
	public Biller updateBiller(Biller biller);
	public List<Biller> getAllBillers();
	public BillerDashDTO billerMoreInfo(Long billerId);
	List<Customer> registeredCustomers(Long billerId);
}
