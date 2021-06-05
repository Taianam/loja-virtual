package br.com.serratec.lojavirtual.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "produtoLong")
public class Produto_Pedidos {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Long produto_id;
	private Long Long_id;
	private Integer quantidade;
	private Double preco;

	public Produto_Pedidos() {
	}

	public Produto_Pedidos(Long id, Long produto_id, Long Long_id, Integer quantidade, Double preco) {
		this.id = id;
		this.produto_id = produto_id;
		this.Long_id = Long_id;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduto_id() {
		return produto_id;
	}

	public void setProduto_id(Long produto_id) {
		this.produto_id = produto_id;
	}

	public Long getLong_id() {
		return Long_id;
	}

	public void setLong_id(Long Long_id) {
		this.Long_id = Long_id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
