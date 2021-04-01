package com.example.features;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class StudyDynamicTest {
    // Collection<DynamicTest>
	// Iterator<DynamicTest>
	// Iterable<DynamicTest>
	// Stream<DynamicTest>	
	@TestFactory
	Collection<DynamicTest> test1(){
		return List.of(
				DynamicTest.dynamicTest(
						"my dynamic test #1", () -> {
							// 1. test fixture/setup
							// 2. call exercise method
							// 3. verification
							// 4. tear-down
							System.err.println("my dynamic test #1");
						}),
				DynamicTest.dynamicTest(
						"my dynamic test #2", () -> {
							// 1. test fixture/setup
							// 2. call exercise method
							// 3. verification
							// 4. tear-down
							System.err.println("my dynamic test #2");
						})
		);
	}
	
	@TestFactory
	// Stream API: Lazy
	Stream<DynamicTest> test2(){
		return List.of(4,8,15,16,23,42).stream()
				   .map( i -> DynamicTest.dynamicTest( "test #"+i, () -> { }));
	}
	
}
