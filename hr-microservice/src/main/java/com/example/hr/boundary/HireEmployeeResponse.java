package com.example.hr.boundary;

public class HireEmployeeResponse {

	private String status;

	public HireEmployeeResponse() {
	}

	public HireEmployeeResponse(String status) {
		this.status = status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
