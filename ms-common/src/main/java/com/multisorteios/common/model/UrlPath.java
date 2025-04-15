package com.multisorteios.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="url_path")
public class UrlPath implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "url", nullable = false, length = 64)
	private String url;
	
	@Column(name = "evento_id", nullable = false)
	private String eventoId;

	@Column(name = "empresa_id", nullable = false)
	private Integer empresaId;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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
	
}