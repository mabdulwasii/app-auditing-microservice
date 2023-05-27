package com.simplifysynergy.cqrs.customer.controller;

import com.simplifysynergy.cqrs.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.simplifysynergy.cqrs.customer.service.CustomerService;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@Slf4j
public class CustomerController {

	private final CustomerService service;

	@PostMapping("/")
	public Mono<Customer> create(@RequestBody Customer customer) {
		log.info("create: {}", customer);
		return service.save(customer);
	}
	
}
