package br.com.serratec.lojavirtual.model.cliente;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.serratec.lojavirtual.model.produto_pedido.Pedidos;
import com.sun.security.auth.UserPrincipal;

@Entity
@SequenceGenerator(name = "generator_cliente", sequenceName = "sequence_cliente", initialValue = 1, allocationSize = 1)
public class Cliente implements UserDetails{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_cliente")
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

	@OneToMany(mappedBy = "cliente")
	private List<Pedidos> pedidos;

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
	public void setPedidos(List<Pedidos> pedidos) {
		this.pedidos = pedidos;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}
	
	
}
