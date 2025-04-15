package com.multisorteios.common.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "titulo_evento")
public class TituloEvento implements Serializable{

	private static final long serialVersionUID = 1L;
	

	@javax.persistence.Id
	private Id id = new Id();
	
	@Column(name = "titulo", nullable = false, length = 60)
	private String titulo;
	
	@Column(name = "subtitulo", nullable = false, length = 100)
	private String subtitulo;


	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	
	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public Id() {
			super();
		}

		public Id(String eventoId, Integer empresaId) {
			super();
			this.eventoId = eventoId;
			this.empresaId = empresaId;
		}

		@Column(name = "evento_id", nullable = false, length = 10)
		private String eventoId;
		
		@Column(name = "empresa_id", nullable = false)
		private Integer empresaId;

		public String getEventoId() {
			return eventoId;
		}

		public void setEventoId(String eventoId) {
			this.eventoId = eventoId;
		}

		public Integer getEmpresaId() {
			return empresaId;
		}

		public void setEmpresaId(Integer empresaId) {
			this.empresaId = empresaId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(empresaId, eventoId);
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
			return Objects.equals(empresaId, other.empresaId) && Objects.equals(eventoId, other.eventoId);
		}

	}

}