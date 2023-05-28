package com.simplifysynergy.cqrs.command.controller;

import com.simplifysynergy.cqrs.command.service.UserService;
import com.simplifysynergy.cqrs.entity.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@Slf4j
public class UserController {

	private final UserService service;

	@PostMapping("/")
	public Mono<User> create(@RequestBody User user) {
		log.info("create: {}", user);
		return service.save(user);
	}
	
}
