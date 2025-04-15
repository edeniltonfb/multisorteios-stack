package com.multisorteios.common.transfer;

import java.io.Serializable;

public class GenericResponseTO <T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean success;
	private String errorMessage;
	private T data;
	
	public GenericResponseTO(boolean success, String errorMessage, T data) {
		super();
		this.success = success;
		this.errorMessage = errorMessage;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
