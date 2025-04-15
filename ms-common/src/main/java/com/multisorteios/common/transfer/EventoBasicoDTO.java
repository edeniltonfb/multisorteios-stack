package com.multisorteios.common.transfer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventoBasicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	@JsonFormat(timezone = "GMT-03:00", shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataSorteio;

	private String horario;

	private String titulo;

	private String situacao;

	private BigDecimal arrecadacao;

	private String tipo;

	private String subtipo;

	private String imageUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDataSorteio() {
		return dataSorteio;
	}

	public void setDataSorteio(Date dataSorteio) {
		this.dataSorteio = dataSorteio;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getArrecadacao() {
		return arrecadacao;
	}

	public void setArrecadacao(BigDecimal arrecadacao) {
		this.arrecadacao = arrecadacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
