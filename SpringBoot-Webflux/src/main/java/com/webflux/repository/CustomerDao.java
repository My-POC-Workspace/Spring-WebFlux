package com.webflux.repository;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.webflux.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerDao {

	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	Traditional Approach
//	Here this method will always to wait for 50 second s and then will display the output
	public List<Customer> getCustomer() {
		return IntStream.rangeClosed(1, 50)
				.peek(CustomerDao::sleepExecution)
				.peek(e -> System.out.println("processing count " + e))
				.mapToObj(e -> new Customer(e, "Customer " + e))
				.collect(Collectors.toList());
	}

	
//	SpringReactive Approach
//	It will not wait, after every stream it will display the output
	public Flux<Customer> getAllCustomerFlux(){
		return Flux.range(0, 50)
			.delayElements(Duration.ofSeconds(1))    // similar to Thread.sleep(1000)
			.doOnNext(e -> System.out.println("Processing Count" +  e)) // similar to peek
			.map(e -> new Customer(e, "Customer" + e)); 
			
	}
	
	// Getting all customer withour any delay...
	public Flux<Customer> getAllCustomer(){
		return Flux.range(0, 50)
			.doOnNext(e -> System.out.println("Processing Count" +  e)) // similar to peek
			.map(e -> new Customer(e, "Customer" + e)); 
			
	}
	
	// Getting customer with id = 5...
	
	
}
