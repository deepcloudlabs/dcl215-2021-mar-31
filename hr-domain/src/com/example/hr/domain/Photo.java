package com.example.hr.domain;

import java.util.Objects;

@ValueObject
public final class Photo {
	private final byte[] data;

	private Photo(byte[] data) {
		this.data = data;
	}

	public static Photo of(byte[] data) {
		Objects.requireNonNull(data);
		return new Photo(data);
	}

	public byte[] getData() {
		return data;
	}

}
