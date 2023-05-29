package com.simplifysynergy.cqrs.command.adapter.controller;

import com.simplifysynergy.cqrs.command.usecase.UserCommandUseCase;
import com.simplifysynergy.cqrs.common.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/user")
public class UserCommandController {

	private final UserCommandUseCase service;

	@PostMapping
	public Mono<User> create(@RequestBody User user) {
		log.info("UserCommandController::create: {}", user);
		return service.create(user);
	}

	@PutMapping
	public Mono<User> update(@RequestBody User user) {
		log.info("UserCommandController::create: {}", user);
		return service.update(user);
	}
	@DeleteMapping("/{id}")
	public Mono<Void> delete(@PathVariable String id) {
		log.info("UserCommandController::delete: {}", id);
		return service.delete(id);
	}

}
