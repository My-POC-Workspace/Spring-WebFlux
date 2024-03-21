package com.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.model.Customer;
import com.webflux.repository.CustomerDao;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GetAllCustomerFluxHandler {
	
	@Autowired
	private CustomerDao customerDao;
	
	public Mono<ServerResponse> getAll(ServerRequest res){
		Flux<Customer> allCustomerFlux = customerDao.getAllCustomerFlux();
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(allCustomerFlux,Customer.class);
	}

}
