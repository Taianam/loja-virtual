package br.com.serratec.lojavirtual.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedidos {
	
	private Long id;
	private Integer numeroDoPedido;
	private ArrayList<Produto> lisraDeProdutos;
	private Double ValorTotalDoPedido;
	private Date dataDoPedido;
	private String status;
	private Long cliente_id;
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
	public ArrayList<Produto> getLisraDeProdutos() {
		return lisraDeProdutos;
	}
	public void setLisraDeProdutos(ArrayList<Produto> lisraDeProdutos) {
		this.lisraDeProdutos = lisraDeProdutos;
	}
	public Double getValorTotalDoPedido() {
		return ValorTotalDoPedido;
	}
	public void setValorTotalDoPedido(Double valorTotalDoPedido) {
		ValorTotalDoPedido = valorTotalDoPedido;
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
