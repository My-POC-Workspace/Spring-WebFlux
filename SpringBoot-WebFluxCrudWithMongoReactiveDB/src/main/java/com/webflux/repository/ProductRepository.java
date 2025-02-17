package com.webflux.repository;

import java.util.function.Function;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.webflux.dto.ProductDto;
import com.webflux.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String>{

	Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);


	
	
}
