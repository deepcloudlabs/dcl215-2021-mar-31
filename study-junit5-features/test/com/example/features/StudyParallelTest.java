package com.example.features;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;

public class StudyParallelTest {
	static int counter = 0;

	@Test
	@ResourceLock(value="shared", mode=ResourceAccessMode.READ_WRITE)
	void test1() {
		counter++;
		System.err.println("test1() :" + counter);
	}

	@Test
	@ResourceLock(value="shared", mode=ResourceAccessMode.READ_WRITE)
	void test2() {
		counter++;
		System.err.println("test2(): " + counter);
	}

	@Test
	@ResourceLock(value="shared", mode=ResourceAccessMode.READ)
	void test3() {
		System.err.println("test3(): "+counter);
	}

	@Test
	@ResourceLock(value="shared", mode=ResourceAccessMode.READ)
	void test4() {
		System.err.println("test4(): " + counter);
	}

	@Test
	void test5() {
		System.err.println("test5()");
	}

	@Test
	void test6() {
		System.err.println("test6()");
	}

	@Test
	void test7() {
		System.err.println("test7()");
	}

	@Test
	void test8() {
		System.err.println("test8()");
	}

}
