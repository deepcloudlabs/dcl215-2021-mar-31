package com.example.domain;

public class Account {
	private final String iban;
	private double balance;

	public Account(String iban) {
		this.iban = iban;
	}

	public Account(String iban, double balance) {
		this.iban = iban;
		this.balance = balance;
	}

	public String getIban() {
		return iban;
	}

	public double getBalance() {
		return balance;
	}

	public boolean withdraw(double amount) {
		if (amount <= 0.0)
			throw new IllegalArgumentException("Amount must be positive.");
		if (amount > balance)
			return false;
		balance -= amount;
		return true;
	}

	public boolean deposit(double amount) {
		if (amount <= 0.0)
			throw new IllegalArgumentException("Amount must be positive.");
		balance += amount;
		return true;
	}
}
