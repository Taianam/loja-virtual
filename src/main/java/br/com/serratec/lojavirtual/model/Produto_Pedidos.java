package br.com.serratec.lojavirtual.model;


import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.serratec.lojavirtual.model.produto.Produto;

@Entity
@Table(name ="produtoPedidos")
public class Produto_Pedidos {
	
	private Long id;
	private Produto produto_id;
	private Pedidos pedidos_id;
	private Integer quantidade;
	private Double preco;
	
	public Produto_Pedidos() {}
	
	public Produto_Pedidos(Long id, Produto produto_id, Pedidos pedidos_id, Integer quantidade, Double preco) {
		this.id = id;
		this.produto_id = produto_id;
		this.pedidos_id = pedidos_id;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Produto getProduto_id() {
		return produto_id;
	}
	public void setProduto_id(Produto produto_id) {
		this.produto_id = produto_id;
	}
	public Pedidos getPedidos_id() {
		return pedidos_id;
	}
	public void setPedidos_id(Pedidos pedidos_id) {
		this.pedidos_id = pedidos_id;
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
