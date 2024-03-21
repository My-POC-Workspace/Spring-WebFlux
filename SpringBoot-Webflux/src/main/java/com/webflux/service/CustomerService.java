package com.webflux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webflux.model.Customer;
import com.webflux.repository.CustomerDao;

import lombok.experimental.Accessors;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	
	// Traditional method
	public List<Customer> loadAllCustomer(){
		long start = System.currentTimeMillis();
		List<Customer> customers = customerDao.getCustomer();
		long end = System.currentTimeMillis();
		System.out.println("Total Execution time --> " + (end - start));
		return customers;
	}
	
	// SpringReactive approach
	public Flux<Customer> loadAllCustomerFlux(){
		long start = System.currentTimeMillis();
		Flux<Customer> customers = customerDao.getAllCustomerFlux();
		long end = System.currentTimeMillis();
		System.out.println("Total Execution time --> " + (end - start));
		return customers;
	}
	

}
