package com.example.hr.boundary;

public class RestErrorMessage {
	private String status;
	private String reason;

	public RestErrorMessage(String status, String reason) {
		this.status = status;
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}

}
