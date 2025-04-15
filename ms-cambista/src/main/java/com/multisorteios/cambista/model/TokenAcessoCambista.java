package com.multisorteios.cambista.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "token_acesso_cambista", uniqueConstraints = @UniqueConstraint(columnNames = { "token" }))
public class TokenAcessoCambista implements Serializable {
	private static final long serialVersionUID = 1L;

	@javax.persistence.Id
	private Id id = new Id();

	@Column(name = "token", nullable = true, length = 20)
	private String token;

	@Column(name = "validade", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date validade;

	@JsonFormat(/* timezone = "GMT-03:00", */shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "atualizacao_data_hora", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	private Date atualizacaoDataHora;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public Date getAtualizacaoDataHora() {
		return atualizacaoDataHora;
	}

	public void setAtualizacaoDataHora(Date atualizacaoDataHora) {
		this.atualizacaoDataHora = atualizacaoDataHora;
	}

	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 1L;

		public Id() {
			super();
		}

		public Id(Integer empresaId, Integer filialId) {
			super();
			this.empresaId = empresaId;
			this.cambistaId = filialId;
		}

		@Column(name = "empresa_id")
		private Integer empresaId;

		@Column(name = "cambista_id", nullable = false)
		private Integer cambistaId;

		public Integer getEmpresaId() {
			return empresaId;
		}

		public void setEmpresaId(Integer empresaId) {
			this.empresaId = empresaId;
		}

		public Integer getCambistaId() {
			return cambistaId;
		}

		public void setCambistaId(Integer filialId) {
			this.cambistaId = filialId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(empresaId, cambistaId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Id other = (Id) obj;
			return Objects.equals(empresaId, other.empresaId) && Objects.equals(cambistaId, other.cambistaId);
		}

	}

}
