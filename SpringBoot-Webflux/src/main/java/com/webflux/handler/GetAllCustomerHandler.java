package com.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.model.Customer;
import com.webflux.repository.CustomerDao;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GetAllCustomerHandler {
	
	@Autowired
	private CustomerDao customerDao;

	public Mono<ServerResponse> getAllCustomerList(ServerRequest res){
		Flux<Customer> allCustomer = customerDao.getAllCustomer();
		return ServerResponse.ok().body(allCustomer, Customer.class);
	}
	
	
	public Mono<ServerResponse> getCustomerWithId(ServerRequest request){
		int id = Integer.valueOf(request.pathVariable("id"));  // here if we don't use interger.ValueOf() then it will return id as string..
		Mono<Customer> customer = customerDao.getAllCustomer().filter(e -> e.getId() == id).next();
//		Mono<Customer> customer = customerDao.getAllCustomer().filter(e -> e.getId() == id).take(1).single(); we can use this also to find single customer
		return ServerResponse.ok().body(customer,Customer.class);
	
	}
	
	
	public Mono<ServerResponse> addCustomer(ServerRequest request){
		Mono<Customer> customer = request.bodyToMono(Customer.class);  
		Mono<String> saveResponse = customer.map(dto -> dto.getId() + " : " + dto.getName());
		return ServerResponse.ok().body(saveResponse,Customer.class);
	}
	
}
