package com.simplifysynergy.cqrs.entity.domain.dto.gateway;

import lombok.AllArgsConstructor;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
class AccountServiceInstanceListSuppler implements ServiceInstanceListSupplier {

    private final String serviceId;

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays
                .asList(new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8080, false)));
    }
}
