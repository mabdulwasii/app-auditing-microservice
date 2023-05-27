package com.simplifysynergy.cqrs.customer.controller;

import com.simplifysynergy.cqrs.customer.repository.AuditRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuditController {

	private final AuditRepository repository;
	
}
