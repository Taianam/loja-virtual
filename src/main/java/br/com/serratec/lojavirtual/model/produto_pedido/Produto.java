package br.com.serratec.lojavirtual.model.produto_pedido;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	private String descricao;
	private Double preco;
	private Integer estoque;
	private String imagem;
	private LocalDate dataDeCadastro;

	@ManyToOne()
	@JoinColumn(name = "categoriaId")
	private Categoria categoria;

	public Produto() {
	}

	public Produto(String nome, String descricao, Double preco, Integer estoque, Long categoria) {

		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.estoque = estoque;
		this.dataDeCadastro = LocalDate.now();

	}

	//#region Getters e Setters

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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public LocalDate getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(LocalDate dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoriaId(Categoria categoria) {
		this.categoria = categoria;
	}


	public String getImagem() {
		return imagem;
	}


	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	//#endregion

	public boolean validarParaCadastro(){
		return (!this.nome.isEmpty() && !this.descricao.isEmpty() && !this.preco.equals(null) && !this.estoque.equals(null));
	}
}
