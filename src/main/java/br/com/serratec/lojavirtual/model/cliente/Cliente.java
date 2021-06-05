package br.com.serratec.lojavirtual.model.cliente;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente {
	 
	private Long id;
	private String email;
	private String nome;
	private String senha;
	private String cpf;
	private String telefone;
	private Date dataDeNascimento;
	private Endereco enderecoId;
	
	public Cliente() {}
	
	public Cliente(
			Long id,
			String email,
			String nome,
			String senha,
			String cpf,
			String telefone,
			Date dataDeNascimento,
			Endereco enderecoId) {
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataDeNascimento = dataDeNascimento;
		this.enderecoId = enderecoId;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public Endereco getEnderecoId() {
		return enderecoId;
	}
	public void setEnderecoId(Endereco enderecoId) {
		this.enderecoId = enderecoId;
	}


}
