package com.example.features;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class AbstractBaseTest {
	@BeforeAll
	static void beforeAllTests() {
		System.err.println("Tests begin");	
	}
	
	@AfterAll
	static void afterAllTests() {
		System.err.println("Tests end");			
	}
}
