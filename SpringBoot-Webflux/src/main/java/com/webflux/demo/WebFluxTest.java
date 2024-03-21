package com.webflux.demo;


import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class WebFluxTest {
	

	public void MonoTest() {
		
		Mono<String> monoString = Mono.just("Rahul").log();
		monoString.subscribe(System.out::println);
		
	}

}
