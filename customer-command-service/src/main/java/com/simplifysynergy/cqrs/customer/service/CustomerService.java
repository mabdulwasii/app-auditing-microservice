package com.simplifysynergy.cqrs.customer.service;

import com.simplifysynergy.cqrs.customer.repository.CustomerRepository;
import com.simplifysynergy.cqrs.domain.Customer;
import com.simplifysynergy.cqrs.dto.AccountDTO;
import com.simplifysynergy.cqrs.utils.CustomerMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository repository;
    private final WebClient.Builder webClientBuilder;

    public Mono<Customer> findById(@PathVariable("id") String id) {
        log.info("findById: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/")
    public Flux<Customer> findAll() {
        log.info("findAll");
        return repository.findAll();
    }

    public Mono<Customer> findByIdWithAccounts(@PathVariable("id") String id) {
        log.info("findByIdWithAccounts: id={}", id);
        Flux<AccountDTO> accounts = webClientBuilder.build()
                .get().uri("http://account-service/customer/{customer}", id)
                .retrieve().bodyToFlux(AccountDTO.class);

        return accounts
                .collectList()
                .map(Customer::new)
                .mergeWith(repository.findById(id))
                .collectList()
                .map(CustomerMapper::map);
    }

    public Mono<Customer> save(Customer customer) {
        log.info("save: {}", customer);
        return repository.save(customer);
    }
}
