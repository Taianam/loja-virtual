package br.com.serratec.lojavirtual.model.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "generator_endereco", sequenceName = "sequence_endereco", initialValue = 1, allocationSize = 1)
public class Endereco {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO, generator = "generator_endereco")
	private Long id;
	private Integer numero;
	private String complemento;
	@Column(nullable = false)
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	
	public Endereco () {}
	
	public Endereco (
			Long id,
			String cep,
			String rua,
			String bairro,
			String cidade,
			Integer numero,
			String complemento,
			String estado) {
		this.id = id;
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.numero = numero;
		this.complemento = complemento;
		this.estado = estado;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
