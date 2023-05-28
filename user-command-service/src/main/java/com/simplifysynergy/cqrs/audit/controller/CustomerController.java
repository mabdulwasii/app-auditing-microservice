package com.simplifysynergy.cqrs.audit.controller;

import com.simplifysynergy.cqrs.entity.domain.User;
import com.simplifysynergy.cqrs.audit.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@Slf4j
public class CustomerController {

	private final CustomerService service;

	@PostMapping("/")
	public Mono<User> create(@RequestBody User user) {
		log.info("create: {}", user);
		return service.save(user);
	}
	
}
