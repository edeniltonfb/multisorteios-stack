package br.com.multisorteios.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome", nullable = false, length = 40)
	private String nome;

	@Column(name = "ativa", nullable = false, length = 1)
	private String ativa;
	
	@Column(name = "email", nullable = true, length = 60)
	private String email;
	
	@Column(name = "site", nullable = true, length = 60)
	private String site;
	
	@Column(name = "alias", nullable = true, length = 16)
	private String alias;
	
	@Column(name = "comissao", nullable = true, length = 4, precision = 4, scale = 2)
	private BigDecimal comissao;
	
	@Column(name = "codigo_vendedor_externo", nullable = true, length = 8)
	private String codigoVendedorExterno;
	
	@Column(name = "senha_vendedor_externo", nullable = true, length = 8)
	private String senhaVendedorExterno;
	
	@Column(name = "whatsapp_access_token", nullable = true, length = 16)
	private String whatsappAccessToken;
	
	@Column(name = "whatsapp_instance_id", nullable = true, length = 16)
	private String whatsappInstanceId;
	
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAtiva() {
		return ativa;
	}

	public void setAtiva(String ativa) {
		this.ativa = ativa;
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

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public String getCodigoVendedorExterno() {
		return codigoVendedorExterno;
	}

	public void setCodigoVendedorExterno(String codigoVendedorExterno) {
		this.codigoVendedorExterno = codigoVendedorExterno;
	}

	public String getSenhaVendedorExterno() {
		return senhaVendedorExterno;
	}

	public void setSenhaVendedorExterno(String senhaVendedorExterno) {
		this.senhaVendedorExterno = senhaVendedorExterno;
	}

	public String getWhatsappAccessToken() {
		return whatsappAccessToken;
	}

	public void setWhatsappAccessToken(String whatsappAccessToken) {
		this.whatsappAccessToken = whatsappAccessToken;
	}

	public String getWhatsappInstanceId() {
		return whatsappInstanceId;
	}

	public void setWhatsappInstanceId(String whatsappInstanceId) {
		this.whatsappInstanceId = whatsappInstanceId;
	}
	
}
