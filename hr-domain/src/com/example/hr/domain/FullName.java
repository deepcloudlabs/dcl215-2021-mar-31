package com.example.hr.domain;

import java.util.Objects;

// Effective Java Programming
@ValueObject // Immutable
public final class FullName {
	private final String firstName;
	private final String lastName;

	private FullName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public static FullName valueOf(String firstName, String lastName) {
		Objects.requireNonNull(firstName);
		Objects.requireNonNull(lastName);
		return new FullName(firstName, lastName);
	}
}
