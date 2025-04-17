package com.multisorteios.common.transfer;

import java.io.Serializable;

public class LoginTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String login;

	private String password;

	private String name;

	private String token;

	private String profile;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
