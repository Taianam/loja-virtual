package br.com.serratec.lojavirtual.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "produtoPedidos")
public class Produto_Pedidos {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Long produto_id;
	private Long pedido_id;
	private Integer quantidade;
	private Double preco;

	public Produto_Pedidos() {
	}


	public Produto_Pedidos(Long id, Long produto_id, Long pedido_id, Integer quantidade, Double preco) {

		this.id = id;
		this.produto_id = produto_id;
		this.pedido_id = pedido_id;
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

	public Long getPedido_id() {
		return pedido_id;
	}


	public void setPedido_id(Long pedido_id) {
		this.pedido_id = pedido_id;
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
