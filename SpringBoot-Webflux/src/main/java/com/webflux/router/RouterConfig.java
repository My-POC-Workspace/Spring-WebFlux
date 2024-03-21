package com.webflux.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.handler.GetAllCustomerFluxHandler;
import com.webflux.handler.GetAllCustomerHandler;

@Configuration
public class RouterConfig {
	
	@Autowired
	private GetAllCustomerHandler customerHandler;
	
	@Autowired
	private GetAllCustomerFluxHandler allCustomerFluxHandler;
	
	@Bean
	public RouterFunction<ServerResponse> routerFunction(){
		
		return RouterFunctions.route()
				.GET("/router/getAllCustomerList", customerHandler::getAllCustomerList)
				.GET("/router/getAllCustomerFlux", allCustomerFluxHandler::getAll)
				.GET("/router/getCustomer/{id}", customerHandler::getCustomerWithId)
				.POST("/router/addCustomer", customerHandler::addCustomer)
				.build();
	}

}
