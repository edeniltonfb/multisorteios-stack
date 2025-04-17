package com.multisorteios.common.model;

import java.io.Serializable;
import java.util.Date;

public abstract class EntityProfileVO implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract Integer getId();

	public abstract void setId(Integer id);

	public abstract Integer getEmpresaId();

	public abstract void setEmpresaId(Integer empresaId);

	public abstract String getNome();

	public abstract void setNome(String nome);

	public abstract String getAtiva();

	public abstract void setAtiva(String ativa);

	public abstract String getEmail();

	public abstract void setEmail(String email);

	public abstract String getWhatsapp();

	public abstract void setWhatsapp(String whatsapp);


	public abstract String getLogin();

	public abstract void setLogin(String login);

	public abstract String getSenha();

	public abstract void setSenha(String senha);

	public abstract Date getAtualizacaoDataHora();

	public abstract void setAtualizacaoDataHora(Date atualizacaoDataHora);

}
