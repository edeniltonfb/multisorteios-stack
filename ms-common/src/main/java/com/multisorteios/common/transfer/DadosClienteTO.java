package com.multisorteios.common.transfer;

import java.io.Serializable;

public class DadosClienteTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;

	private String email;

	private String telefone;

	private String cidade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeCompleto) {
		this.nome = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
