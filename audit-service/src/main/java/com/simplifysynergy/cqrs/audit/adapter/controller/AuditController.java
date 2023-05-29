package com.simplifysynergy.cqrs.audit.adapter.controller;

import com.simplifysynergy.cqrs.audit.usecase.AuditUseCase;
import com.simplifysynergy.cqrs.common.domain.UserAudit;
import com.simplifysynergy.cqrs.common.enumeration.EventType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/audit")
public class AuditController {

	private final AuditUseCase useCase;

	@GetMapping
	public Flux<UserAudit> findAll() {
		log.info("AuditController: findAll");
		return useCase.findAll();
	}

	@GetMapping("/{uuid}")
	public Mono<UserAudit> findAuditById(@PathVariable String uuid) {
		log.info("AuditController: findAuditById {}", uuid);
		return useCase.findAuditById(uuid);
	}

	@GetMapping("/{start}/{end}")
	public Flux<UserAudit> findAuditBetween(@PathVariable LocalDateTime start, @PathVariable LocalDateTime end) {
		log.info("AuditController: findAuditBetween {} , {}", start, end);
		return useCase.findAuditBetween(start, end);
	}

	@GetMapping("/type/{type}")
	public Flux<UserAudit> findAuditByType(@PathVariable EventType type) {
		log.info("AuditController: findAuditByType {} ", type);
		return useCase.findAuditByType(type);
	}

	@GetMapping("/type/{type}/{start}/{end}")
	public Flux<UserAudit> findAuditByTypeBetween(
			@PathVariable EventType type,
			@PathVariable LocalDateTime start,
			@PathVariable LocalDateTime end
	) {
		log.info("AuditController: findAuditByTypeBetween {}, {}, {} ", type, start, end);
		return useCase.findAuditByTypeBetween(type, start, end);
	}
}
