package com.global.response;

public class ApiResponse {

	private String message;
	private Object data;
	
	
	public ApiResponse(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	
	
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
