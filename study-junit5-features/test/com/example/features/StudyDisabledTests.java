package com.example.features;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("This test contains disabled test(s)")
public class StudyDisabledTests extends AbstractBaseTest {

	@Test
	@Disabled("Skip test1 for this time!")
	public void test1() {

	}

	@Test
	@Disabled("Skip test1 for this time!")
	public void test2() {

	}

	@Test
	@Disabled("Skip test1 for this time!")
	public void test3() {

	}
}
