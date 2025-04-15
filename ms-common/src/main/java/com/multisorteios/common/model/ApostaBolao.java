package com.multisorteios.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

@Entity
@Table(name = "aposta_bolao", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "evento_id", "chave" }) })
//@Audited
public class ApostaBolao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "bilhete_id", nullable = false)
	private String bilheteId;

	@Column(name = "evento_id", nullable = false)
	private String eventoId;

	@Column(name = "chave", nullable = false, length = 10)//
	private String chave;
	
	@Column(name = "numero", nullable = false, length = 25)//
	private String numero;
	
	@Column(name = "conferencia", nullable = true, length = 100)//
	private String conferencia;
	
	@Column(name = "acertos", nullable = false)//
	private Integer acertos;
	
	@Column(name = "atualizacao_data_hora", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Version 
	private Date atualizacaoDataHora;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBilheteId() {
		return bilheteId;
	}

	public void setBilheteId(String bilheteId) {
		this.bilheteId = bilheteId;
	}

	public String getEventoId() {
		return eventoId;
	}

	public void setEventoId(String eventoId) {
		this.eventoId = eventoId;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Date getAtualizacaoDataHora() {
		return atualizacaoDataHora;
	}

	public void setAtualizacaoDataHora(Date atualizacaoDataHora) {
		this.atualizacaoDataHora = atualizacaoDataHora;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getConferencia() {
		return conferencia;
	}

	public void setConferencia(String conferencia) {
		this.conferencia = conferencia;
	}

	public Integer getAcertos() {
		return acertos;
	}

	public void setAcertos(Integer acertos) {
		this.acertos = acertos;
	}

	@Override
	public String toString() {
		return "ApostaBolao [id=" + id + ", bilheteId=" + bilheteId + ", eventoId=" + eventoId + ", chave=" + chave
				+ ", numero=" + numero + ", conferencia=" + conferencia + ", acertos=" + acertos
				+ ", atualizacaoDataHora=" + atualizacaoDataHora + "]";
	}
	
}
