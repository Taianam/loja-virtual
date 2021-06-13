package br.com.serratec.lojavirtual.model.produto_pedido;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "generator_categoria", sequenceName = "sequence_categoria", initialValue = 1, allocationSize = 1)
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_categoria" )
	private Long id;

	@Column(nullable = false)
	private String nome;


	private String descricao;

	@OneToMany(mappedBy = "categoria")
	private List<Produto> produtos;

	public Categoria() {
	}

	public Categoria(Long id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean validarParaCadastro(){
		return !this.nome.isEmpty() && !this.descricao.isEmpty();
	}
}
