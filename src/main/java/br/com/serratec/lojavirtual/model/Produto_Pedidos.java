package br.com.serratec.lojavirtual.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="produtoPedidos")
public class Produto_Pedidos {
	
	private Long id;
	private Long produto_id;
	private Long pedidos_id;
	private Integer quantide;
	private Double preco;
	
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
	public Long getPedidos_id() {
		return pedidos_id;
	}
	public void setPedidos_id(Long pedidos_id) {
		this.pedidos_id = pedidos_id;
	}
	public Integer getQuantide() {
		return quantide;
	}
	public void setQuantide(Integer quantide) {
		this.quantide = quantide;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	
}
