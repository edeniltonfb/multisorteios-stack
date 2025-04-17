package com.multisorteios.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "whatsapp_empresa")
public class WhatsAppEmpresa  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "token", nullable = false, length = 12)
	private String id;

	@Column(name = "empresa_id", nullable = false)
	private Integer empresaId;
	
	@Column(name = "client_token_zapi", nullable = true, length = 60)
	private String clientTokenZapi;
	
	@Column(name = "instance_id_zapi", nullable = true, length = 32)
	private String intanceIdZapi;
	
	@Column(name = "instance_token_zapi", nullable = true, length = 32)
	private String instanceTokenZapi;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

	public String getClientTokenZapi() {
		return clientTokenZapi;
	}

	public void setClientTokenZapi(String clientTokenZapi) {
		this.clientTokenZapi = clientTokenZapi;
	}

	public String getIntanceIdZapi() {
		return intanceIdZapi;
	}

	public void setIntanceIdZapi(String intanceIdZapi) {
		this.intanceIdZapi = intanceIdZapi;
	}

	public String getInstanceTokenZapi() {
		return instanceTokenZapi;
	}

	public void setInstanceTokenZapi(String instanceTokenZapi) {
		this.instanceTokenZapi = instanceTokenZapi;
	}
	
	
}
