package com.example.features;

import org.junit.jupiter.api.Test;

public class StudyConditionalTest {

	@Test
	@ConditionalTestOperatingSystem(value = {OperatingSystemEnum.UNIX})
	public void test1() {
		System.err.println("test1()");
	}
	
	@Test
	@ConditionalTestOperatingSystem(value = {OperatingSystemEnum.WINDOWS})
	public void test2() {
		System.err.println("test2()");
	}
	
	@Test
	@ConditionalTestOperatingSystem(value = {OperatingSystemEnum.UNIX, OperatingSystemEnum.WINDOWS})
	public void test3() {
		System.err.println("test3()");		
	}
}
