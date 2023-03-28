package com.thalos.dto;

import org.springframework.http.HttpStatus;

public class ApiErrorDTO {

	private HttpStatus status;

	public ApiErrorDTO() {}

	public ApiErrorDTO(HttpStatus status) {
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
