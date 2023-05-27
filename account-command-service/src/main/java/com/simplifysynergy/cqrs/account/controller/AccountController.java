package com.simplifysynergy.cqrs.account.controller;


import com.simplifysynergy.cqrs.account.service.AccountService;
import com.simplifysynergy.cqrs.domain.Account;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	private final AccountService service;

	@PostMapping("/")
	public Mono<Account> create(@RequestBody Account account) {
		LOGGER.info("create: {}", account);
		return service.save(account);
	}
}
