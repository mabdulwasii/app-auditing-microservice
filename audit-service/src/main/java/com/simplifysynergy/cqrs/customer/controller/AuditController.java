package com.simplifysynergy.cqrs.customer.controller;

import com.simplifysynergy.cqrs.customer.model.Customer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import pl.piomin.service.customer.model.Account;
import com.simplifysynergy.cqrs.customer.repository.CustomerRepository;
import com.simplifysynergy.cqrs.customer.utils.CustomerMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AuditController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	private final CustomerRepository repository;

    private final WebClient.Builder webClientBuilder;

	private final AccountRepository repository;


	@GetMapping("/customer/{customer}")
	public Flux<Account> findByCustomer(@PathVariable("customer") String customerId) {
		LOGGER.info("findByCustomer: customerId={}", customerId);
		return repository.findByCustomerId(customerId);
	}

	@GetMapping("/")
	public Flux<Account> findAll() {
		LOGGER.info("findAll");
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Account> findById(@PathVariable("id") String id) {
		LOGGER.info("findById: id={}", id);
		return repository.findById(id);
	}

	@PostMapping("/")
	public Mono<Account> create(@RequestBody Account account) {
		LOGGER.info("create: {}", account);
		return repository.save(account);
	}


	@GetMapping("/{id}")
	public Mono<Customer> findById(@PathVariable("id") String id) {
		LOGGER.info("findById: id={}", id);
		return repository.findById(id);
	}

	@GetMapping("/")
	public Flux<Customer> findAll() {
		LOGGER.info("findAll");
		return repository.findAll();
	}

	@GetMapping("/{id}/with-accounts")
	public Mono<Customer> findByIdWithAccounts(@PathVariable("id") String id) {
		LOGGER.info("findByIdWithAccounts: id={}", id);
		Flux<Account> accounts = webClientBuilder.build().get().uri("http://account-service/customer/{customer}", id).retrieve().bodyToFlux(Account.class);		
		return accounts
				.collectList()
				.map(Customer::new)
				.mergeWith(repository.findById(id))
				.collectList()
				.map(CustomerMapper::map);
	}

	@PostMapping("/")
	public Mono<Customer> create(@RequestBody Customer customer) {
		LOGGER.info("create: {}", customer);
		return repository.save(customer);
	}
	
}
