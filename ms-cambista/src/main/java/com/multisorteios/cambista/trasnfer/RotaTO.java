package com.multisorteios.cambista.trasnfer;

import java.io.Serializable;

public class RotaTO implements Serializable {

	/**
	 * Filial
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String nome;
	
	private Integer empresaId;
	
	private boolean ativo = true;
	
	public RotaTO() {
		super();
	}

	public RotaTO(Integer id, String nome, boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Integer getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Integer matrizId) {
		this.empresaId = matrizId;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
