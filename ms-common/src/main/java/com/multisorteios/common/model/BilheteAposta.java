package com.multisorteios.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bilhete_aposta")
public class BilheteAposta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bilhete_id", nullable = false)
	private String bilheteId;
	
	@Column(name = "aposta_list", nullable = false)
	private byte[] apostaList;

	@JsonIgnore
	@Column(name = "atualizacao_data_hora", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Version private Date atualizacaoDataHora;

	public String getBilheteId() {
		return bilheteId;
	}

	public void setBilheteId(String bilheteId) {
		this.bilheteId = bilheteId;
	}

	public byte[] getApostaList() {
		return apostaList;
	}

	public void setApostaList(byte[] apostaList) {
		this.apostaList = apostaList;
	}

	public Date getAtualizacaoDataHora() {
		return atualizacaoDataHora;
	}

	public void setAtualizacaoDataHora(Date atualizacaoDataHora) {
		this.atualizacaoDataHora = atualizacaoDataHora;
	}

}
