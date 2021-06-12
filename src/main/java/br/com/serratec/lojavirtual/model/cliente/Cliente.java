package br.com.serratec.lojavirtual.model.cliente;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "generator_cliente", sequenceName = "sequence_cliente", initialValue = 1, allocationSize = 1)
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gerator_cliente")
	private Long id;
	
	@Column(unique = true)
	private String email;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, length = 8)
	private String senha;
	
	@Column( length = 11, nullable = false)
	private String cpf;

	private String telefone;
	private LocalDate dataDeNascimento;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
	private Endereco endereco;

	public Cliente() {
	}

	public Cliente(Long id, String email, String nome, String senha, String cpf, String telefone, LocalDate dataDeNascimento,
			Endereco endereco) {
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataDeNascimento = dataDeNascimento;
		this.endereco = endereco;
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

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		
		this.dataDeNascimento = dataDeNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco= endereco;
	}

	//#endregion

}
