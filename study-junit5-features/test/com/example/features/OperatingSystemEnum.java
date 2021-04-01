package com.example.features;

public enum OperatingSystemEnum {
	UNIX, WINDOWS;

	static OperatingSystemEnum determineCurrentOs() {
		var osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win"))
			return WINDOWS;
		return UNIX;
	}
}
