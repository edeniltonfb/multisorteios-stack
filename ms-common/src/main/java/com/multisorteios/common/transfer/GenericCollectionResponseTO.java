package com.multisorteios.common.transfer;

import java.io.Serializable;
import java.util.List;

public class GenericCollectionResponseTO <T extends Serializable, L extends List<T>> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean success;
	private String errorMessage;
	private List<T> data;
	
	public GenericCollectionResponseTO(boolean success, String errorMessage, List<T> data) {
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

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
