package com.multisorteios.common.model.fake;

import java.io.Serializable;

public class MensagemZAPI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String phone;

	private String message;
	
	private String image;
	
	private String caption;

	public MensagemZAPI() {
		super();
	}

	public MensagemZAPI(String phone, String image, String caption) {
		super();
		this.phone = phone;
		this.image = image;
		this.caption = caption;
	}

	public MensagemZAPI(String phone, String message) {
		super();
		this.phone = phone;
		this.message = message;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	
}
