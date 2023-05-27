package com.simplifysynergy.cqrs.account.controller;


import com.simplifysynergy.cqrs.account.service.AccountService;
import com.simplifysynergy.cqrs.domain.Account;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	private final AccountService service;

	@GetMapping("/customer/{customer}")
	public Flux<Account> findByCustomer(@PathVariable("customer") String customerId) {
		LOGGER.info("AccountController findByCustomer: customerId = {}", customerId);
		return service.findByCustomer(customerId);
	}

	@GetMapping("/")
	public Flux<Account> findAll() {
		LOGGER.info("AccountController findAll");
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Account> findById(@PathVariable("id") String id) {
		LOGGER.info("AccountController findById: id = {}", id);
		return service.findById(id);
	}
}
