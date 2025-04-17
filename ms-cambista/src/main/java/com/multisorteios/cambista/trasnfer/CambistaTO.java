package com.multisorteios.cambista.trasnfer;

import java.io.Serializable;
import java.math.BigDecimal;

public class CambistaTO implements Serializable {

	/**
	 * Filial
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String nome;
	
	private BigDecimal comissao;

	private String email;
	
	private String whatsapp;

	private Integer rotaId;
	
	private boolean ativo = true;
	
	
	public CambistaTO(Integer id, String nome, BigDecimal comissao, String email, String whatsapp, Integer rotaId,
			boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.comissao = comissao;
		this.email = email;
		this.whatsapp = whatsapp;
		this.rotaId = rotaId;
		this.ativo = ativo;
	}


	public CambistaTO() {
		super();
	}

	public Integer getRotaId() {
		return rotaId;
	}

	public void setRotaId(Integer rotaId) {
		this.rotaId = rotaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

}
