package br.com.serratec.lojavirtual.model.produto_pedido;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedidos {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Integer numeroDoPedido;
	private ArrayList<Produto> listaDeProdutos;
	private Double valorTotalDoPedido;
	private Date dataDoPedido;
	private String status;
	private Long cliente_id;

	public Pedidos() {
	}

	public Pedidos(Integer numeroDoPedido, ArrayList<Produto> listaDeProdutos, Double valorTotalDoPedido,
			Date dataDoPedido, String status, Long cliente_id) {

		this.numeroDoPedido = numeroDoPedido;
		this.listaDeProdutos = listaDeProdutos;
		this.valorTotalDoPedido = valorTotalDoPedido;
		this.dataDoPedido = dataDoPedido;
		this.status = status;
		this.cliente_id = cliente_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroDoPedido() {
		return numeroDoPedido;
	}

	public void setNumeroDoPedido(Integer numeroDoPedido) {
		this.numeroDoPedido = numeroDoPedido;
	}

	public ArrayList<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(ArrayList<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}

	public Double getValorTotalDoPedido() {
		return valorTotalDoPedido;
	}

	public void setValorTotalDoPedido(Double valorTotalDoPedido) {
		this.valorTotalDoPedido = valorTotalDoPedido;
	}

	public Date getDataDoPedido() {
		return dataDoPedido;
	}

	public void setDataDoPedido(Date dataDoPedido) {
		this.dataDoPedido = dataDoPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Long cliente_id) {

		this.cliente_id = cliente_id;
	}

}