package com.simplifysynergy.cqrs.audit.adapter.controller;

import com.simplifysynergy.cqrs.audit.usecase.AuditUseCase;
import com.simplifysynergy.cqrs.entity.domain.Audit;
import com.simplifysynergy.cqrs.entity.enumeration.EventType;
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
	public Flux<Audit> findAll() {
		log.info("AuditController: findAll");
		return useCase.findAll();
	}

	@GetMapping("/{uuid}")
	public Mono<Audit> findAuditById(@PathVariable String uuid) {
		log.info("AuditController: findAuditById {}", uuid);
		return useCase.findAuditById(uuid);
	}

	@GetMapping("/{start}/{end}")
	public Flux<Audit> findAuditBetween(@PathVariable LocalDateTime start, @PathVariable LocalDateTime end) {
		log.info("AuditController: findAuditBetween {} , {}", start, end);
		return useCase.findAuditBetween(start, end);
	}

	@GetMapping("/type/{type}")
	public Flux<Audit> findAuditByType(@PathVariable EventType type) {
		log.info("AuditController: findAuditByType {} ", type);
		return useCase.findAuditByType(type);
	}

	@GetMapping("/type/{type}/{start}/{end}")
	public Flux<Audit> findAuditByTypeBetween(
			@PathVariable EventType type,
			@PathVariable LocalDateTime start,
			@PathVariable LocalDateTime end
	) {
		log.info("AuditController: findAuditByTypeBetween {}, {}, {} ", type, start, end);
		return useCase.findAuditByTypeBetween(type, start, end);
	}
}