package com.multisorteios.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@javax.persistence.Id
	private Id id = new Id();

	@Column(name = "nome", nullable = false, length = 40)
	private String nome;
	
	@Column(name = "cidade", nullable = false, length = 40)
	private String cidade;

	@Column(name = "email", nullable = true, length = 60)
	private String email;
	
	@Column(name = "nickname", nullable = true, length = 40, updatable = true)
	private String nickname;
	
	@JsonFormat(/*timezone = "GMT-03:00", */shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getAtualizacaoDataHora() {
		return atualizacaoDataHora;
	}

	public void setAtualizacaoDataHora(Date atualizacaoDataHora) {
		this.atualizacaoDataHora = atualizacaoDataHora;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 1L;
		

		public Id() {
			super();
		}

		public Id(Integer empresaId, String usuarioId) {
			super();
			this.empresaId = empresaId;
			this.usuarioId = usuarioId;
		}

		@Column(name = "empresa_id")
		private Integer empresaId;

		@Column(name = "usuario_id", nullable = false, length = 11)
		private String usuarioId;

		public Integer getEmpresaId() {
			return empresaId;
		}

		public void setEmpresaId(Integer empresaId) {
			this.empresaId = empresaId;
		}

		public String getUsuarioId() {
			return usuarioId;
		}

		public void setUsuarioId(String usuarioId) {
			this.usuarioId = usuarioId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(empresaId, usuarioId);
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
			return Objects.equals(empresaId, other.empresaId) && Objects.equals(usuarioId, other.usuarioId);
		}

	}


}
