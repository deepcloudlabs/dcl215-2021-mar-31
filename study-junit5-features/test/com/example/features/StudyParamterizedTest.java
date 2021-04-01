package com.example.features;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class StudyParamterizedTest {
	@ParameterizedTest
	@ValueSource(ints = { 4, 8, 15, 16, 23, 42 })
	void test1(int num) {
		System.err.println("Running the test for the number "+num);
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "jack", "kate", "james", "hugo", "jin", "sun" })
	@NullAndEmptySource
	void test2(String name) {
		System.err.println("Running the test for the name "+name);
	}
	
	@ParameterizedTest
	@EnumSource(CryptoCurrency.class)
	void test3(CryptoCurrency currency) {
		System.err.println("Running the test for the currency "+currency.name());
	}

	@ParameterizedTest
	@MethodSource({"generateSeqNums", "generateRandNums"})
	void test4(int val) {
		System.err.println("Running the test for the integer "+val);
	}
		
	@ParameterizedTest
	@CsvSource({
		"ankara,312",
		"istanbul-anadolu,216",
		"istanbul-avrupa,212"
    })
	void test5(String city,int areaCode) {
		System.err.println("Running the test for the city "+city+" : "+areaCode);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = {"/codes-tur.csv","/codes-usa.csv"})
	void test6(String city,int areaCode) {
		System.err.println("Running the test for the city "+city+" : "+areaCode);
	}
	
	static public List<Integer> generateSeqNums(){
		return IntStream.range(1, 11).boxed().collect(Collectors.toList());
	}
	
	static public List<Integer> generateRandNums(){
		return ThreadLocalRandom.current().ints(1,100).limit(10).boxed().collect(Collectors.toList());
	}
	
}
