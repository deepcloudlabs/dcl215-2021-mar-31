package com.example.features;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class YetAnotherTestLifeCycle extends AbstractBaseTest {

	@Test
	@DisplayName("My Test #1")
	void test1() {
		System.err.println("Test #1");
	}

	@Test
	@DisplayName("My Test #2")
	void test2() {
		System.err.println("Test #2");
	}
	
}
