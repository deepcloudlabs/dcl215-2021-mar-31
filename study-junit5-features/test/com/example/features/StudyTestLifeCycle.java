package com.example.features;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@DisplayName("Studying test life cycle annotations")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class StudyTestLifeCycle extends AbstractBaseTest {

	@BeforeEach
	void beforeEachTest() {
		System.err.println("beforeEachTest");	
	}
	
	@AfterEach
	void afterEachTest() {
		System.err.println("afterEachTest");			
	}
	
	@Test
	@DisplayName("Testing class A's fun method")
	@Order(3)
	void test1() {
		System.err.println("test1");			
	}

	@Test
	@DisplayName("Testing class A's gun method")
	@Order(2)
	void test2() {
		System.err.println("test2");			
	}
	
	@Test
	@DisplayName("Testing class A's sun method")
	@Order(1)
	void test3() {
		System.err.println("test3");			
	}

}
