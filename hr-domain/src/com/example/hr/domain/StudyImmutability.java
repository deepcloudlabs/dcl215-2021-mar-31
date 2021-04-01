package com.example.hr.domain;

public class StudyImmutability {

	public static void main(String[] args) {
		Integer u = Integer.valueOf(42);
		Integer v = 42;
		Integer x = 549;
		Integer y = 549;
		System.err.println("u==v: "+(u==v));
		System.err.println("x==y: "+(x==y));

	}

}
