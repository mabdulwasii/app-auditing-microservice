package com.simplifysynergy.cqrs.audit.controller;

import com.simplifysynergy.cqrs.audit.service.CustomerService;
import com.simplifysynergy.cqrs.entity.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@Slf4j
public class CustomerController {

	private final CustomerService service;
	@GetMapping("/{id}")
	public Mono<User> findById(@PathVariable("id") String id) {
		log.info("findById: id={}", id);
		return service.findById(id);
	}

	@GetMapping("/")
	public Flux<User> findAll() {
		log.info("findAll");
		return service.findAll();
	}

	@GetMapping("/{id}/with-accounts")
	public Mono<User> findByIdWithAccounts(@PathVariable("id") String id) {
		log.info("findByIdWithAccounts: id={}", id);
		return service.findByIdWithAccounts(id);
	}
}
