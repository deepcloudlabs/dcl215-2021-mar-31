package com.example.hr.events;

import com.example.hr.domain.TcKimlikNo;

// Immutable
public class EmployeeHiredEvent {

	private final TcKimlikNo identity;

	public EmployeeHiredEvent(TcKimlikNo identity) {
		this.identity = identity;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

	@Override
	public String toString() {
		return "EmployeeHiredEvent [identity=" + identity + "]";
	}

}
