package com.simplifysynergy.cqrs.utils;

import com.simplifysynergy.cqrs.domain.Customer;

import java.util.List;

public class CustomerMapper {

	public static Customer map(List<Customer> customers) {
		Customer customer = new Customer();
		for (Customer c : customers) {
			if (c.getAccountDTOS() != null) customer.setAccountDTOS(c.getAccountDTOS());
			if (c.getAge() != 0) customer.setAge(c.getAge());
			if (c.getFirstName() != null) customer.setFirstName(c.getFirstName());
			if (c.getLastName() != null) customer.setFirstName(c.getLastName());
		}
		return customer;
	}
	
}
