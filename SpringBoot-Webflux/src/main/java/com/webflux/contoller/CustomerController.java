package com.webflux.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.model.Customer;
import com.webflux.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	

	@GetMapping("/getAllCustomer") // Execution time 50052
	public List<Customer> getAllCustomer(){
		return customerService.loadAllCustomer();
	}
	
//	Example of Asynchronous and non blocking
	@GetMapping(value = "/getAllFluxCustomer",produces = MediaType.TEXT_EVENT_STREAM_VALUE) // Execution time 4
	public Flux<Customer> getAllCustomerFlux(){
		return customerService.loadAllCustomerFlux();
	}

}
