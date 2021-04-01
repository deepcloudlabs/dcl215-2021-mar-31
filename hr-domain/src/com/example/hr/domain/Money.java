package com.example.hr.domain;

import java.util.Objects;

// immutable
// public record Money(double value, FiatCurrency currency) {}

@ValueObject // record
public final class Money {
	private final double value;
	private final FiatCurrency currency;

	private Money(double value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}

	public double getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public static Money of(double value, FiatCurrency currency) {
		if (value < 0)
			throw new IllegalArgumentException("Value must be positive");
		Objects.requireNonNull(currency);
		return new Money(value, currency);
	}

	public static Money of(double value) {
		return of(value, FiatCurrency.TL);
	}
}
