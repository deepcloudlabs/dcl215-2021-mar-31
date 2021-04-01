package com.example.hr.domain;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BirthYearTest {
	@Test
	void creatingBirthYearSuccessfuly() {
		BirthYear year = BirthYear.valueOf(1965);
		assertEquals(1965, year.getValue());
	}

	@Test
	void creatingBirthYearFails() {
		assertThrows(IllegalArgumentException.class, () -> BirthYear.valueOf(1941));
	}
}
