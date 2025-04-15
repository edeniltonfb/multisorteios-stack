package com.multisorteios.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "preco_evento")
public class PrecoEvento implements Serializable {
	private static final long serialVersionUID = 1L;

	@javax.persistence.Id
	private Id id = new Id();

	@Column(name = "valor", nullable = false, precision = 5, scale = 2)
	private BigDecimal valor;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 1L;

		public Id() {
			super();
		}

		public Id(String eventoId, Integer empresaId, Integer quantidade) {
			super();
			this.eventoId = eventoId;
			this.empresaId = empresaId;
			this.quantidade = quantidade;
		}

		@Column(name = "evento_id", nullable = false, length = 10)
		private String eventoId;

		@Column(name = "empresa_id", nullable = false)
		private Integer empresaId;

		@Column(name = "quantidade", nullable = false)
		private Integer quantidade;

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

		public Integer getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(Integer quantidade) {
			this.quantidade = quantidade;
		}

		@Override
		public int hashCode() {
			return Objects.hash(empresaId, eventoId, quantidade);
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
			return Objects.equals(empresaId, other.empresaId) && Objects.equals(eventoId, other.eventoId)
					&& Objects.equals(quantidade, other.quantidade);
		}

	}

}
