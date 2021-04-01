package com.example.features;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

public class StudyTaggedTests {


	@FunctionalTest
	void test1() {
		
	}

	@Test
	@Tag("functional")
	void test4() {
		
	}

	@StressTest
	void test5() {
		
	}
	
	@Test
	@Tag("load")
	@Tag("stress")
	@Tag("functional")
	void test2() {
		
	}
	
	@Test
	@Tags({
		@Tag("load"),
		@Tag("functional"),
		@Tag("stress")
	})
	void test3() {
		
	}
}
