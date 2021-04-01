package com.example;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class StudyFunctionalProgramming {
	public static void main(String[] args) {
		List<Integer> numbers = List.of(4,8,15,16,23,42);
		int sum = 0;
		// external loop -> List
		for (int number : numbers) {
			if (number%2 == 0) {
				int squared = number * number;
				sum += squared;
			}
		}
		System.err.println("Sum: "+sum);
		// java 8 -> functional : i) stream api ii) lambda expression + method reference
		//                        iii) Filter/Map/Reduce (Hadoop)
		// Functional Interface -> Single Abstract Method
		// Internal Loop
		Predicate<Integer> even = x -> x%2 == 0 ;
		Function<Integer,Integer> square = u -> u*u ;
		// BinaryOperator<Integer> add = (acc,v) -> acc+v;
		BinaryOperator<Integer> add = Integer::sum; // method reference
		sum = numbers.stream()
				     .parallel()
				     .filter(even)
				     .map(square)
				     .reduce(0, add);
	}
}
