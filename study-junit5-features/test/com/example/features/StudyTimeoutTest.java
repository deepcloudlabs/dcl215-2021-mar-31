package com.example.features;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class StudyTimeoutTest {
	@Test
	@Timeout(unit = TimeUnit.SECONDS, value = 5)
	void test1() throws Throwable {
		TimeUnit.SECONDS.sleep(60);
	}
}
