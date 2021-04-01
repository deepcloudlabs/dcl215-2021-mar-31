package com.example.features;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class StudyRepeatedTest {

	@RepeatedTest(10)
	void test1(RepetitionInfo repInfo) {
		System.err.println(String.format("Running %d of %d", 
				repInfo.getCurrentRepetition(),
				repInfo.getTotalRepetitions()));
	}
}
