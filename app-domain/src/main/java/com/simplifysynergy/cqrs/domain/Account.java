package com.simplifysynergy.cqrs.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document
public class Account {

	@Id
	private String id;
	private String number;
	private String customerId;
	private int balance;
	
	public Account(String number, String customerId, int balance) {
		this.number = number;
		this.customerId = customerId;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", number=" + number + ", customerId=" + customerId +
				", balance=" + balance + "]";
	}

}
