package com.cepheid.cloud.skel.CustomException;

public class BusinessException extends RuntimeException {

	private int errorId;

	private String errorMessage;

	public BusinessException() {

	}

	public BusinessException(int errorId, String errorMessage) {
		super();
		this.errorId = errorId;
		this.errorMessage = errorMessage;
	}

	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
