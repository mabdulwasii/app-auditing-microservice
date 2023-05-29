package com.simplifysynergy.cqrs.query.adapter.controller;

import com.simplifysynergy.cqrs.query.usecase.UserQueryUseCase;
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
public class UserQueryController {

	private final UserQueryUseCase service;
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
}
