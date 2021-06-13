package br.com.serratec.lojavirtual.dto;

import java.time.LocalDate;

import br.com.serratec.lojavirtual.model.cliente.Endereco;


public class AtualizarCliente {
	
	private String novoEmail;
	private String nome;
	private String senha;
	private String novaSenha;
	private String cpf;
	private String telefone;
	private LocalDate dataDeNascimento;
	private	Endereco endereco;

	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public String getNovoEmail() {
		return novoEmail;
	}
	public void setNovoEmail(String novoEmail) {
		this.novoEmail = novoEmail;
	}
	public String getNovaSenha() {
		return novaSenha;
	}
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	
}
