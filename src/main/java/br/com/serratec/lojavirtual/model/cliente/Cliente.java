package br.com.serratec.lojavirtual.model.cliente;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String email;
	private String nome;
	private String senha;
	
	@Column( length = 11, nullable = false)
	private String cpf;
	private String telefone;
	private Date dataDeNascimento;
	private Long enderecoId;

	public Cliente() {
	}

	public Cliente(Long id, String email, String nome, String senha, String cpf, String telefone, Date dataDeNascimento,
			Long enderecoId) {
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataDeNascimento = dataDeNascimento;
		this.enderecoId = enderecoId;
	}

	//#region Getters e Setters

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

	public Long getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Long enderecoId) {
		this.enderecoId = enderecoId;
	}

	//#endregion

	public Boolean validoParaCadastro(){
		return (!this.nome.isEmpty() && !this.email.isEmpty() && !this.senha.isEmpty() && !this.cpf.isEmpty());
	}
}
