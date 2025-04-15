package com.multisorteios.common.transfer;

import java.io.Serializable;

public class EmptyResponseTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean success;
	private String errorMessage;
	
	public EmptyResponseTO(boolean success, String errorMessage) {
		super();
		this.success = success;
		this.errorMessage = errorMessage;
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

}
