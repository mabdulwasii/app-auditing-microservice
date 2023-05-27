package com.simplifysynergy.cqrs.domain;

import com.simplifysynergy.cqrs.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Customer {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private int age;
	@Transient
	private List<AccountDTO> accountDTOS;

	public Customer(List<AccountDTO> accountDTOS) {
		this.accountDTOS = accountDTOS;
	}

	public Customer(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
