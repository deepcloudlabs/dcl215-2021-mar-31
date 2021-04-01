package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Testing value object \"TcKimlikNo\"")
class TcKimlikNoTest {

	@ParameterizedTest
	@ValueSource(strings = { "53283808031", "40562111201", "64681461445", "79390213421" })
	@NullAndEmptySource
	@DisplayName("Creating TcKimlikNo with an invalid identity no throws IllegalArgumentException")
	void testInvalidTcKimlikNo(String identity) {
		assertThrows(IllegalArgumentException.class, () -> TcKimlikNo.of(identity));
	}

	@ParameterizedTest
	@ValueSource(strings = { "53283808030", "40562111202", "64681461444", "79390213420" })
	@DisplayName("Creating TcKimlikNo with a valid identity successfuly")
	void testValidTcKimlikNo(String identity) {
		var kimlik = TcKimlikNo.of(identity);
		assertEquals(identity, kimlik.getValue());
	}

	@Test
	@DisplayName("Test object caching")
	void testTcKimlikNoCaching() {
		var kimlik1 = TcKimlikNo.of("11111111110");
		var kimlik2 = TcKimlikNo.of("11111111110");
		assertTrue(kimlik1 == kimlik2);
	}

}
