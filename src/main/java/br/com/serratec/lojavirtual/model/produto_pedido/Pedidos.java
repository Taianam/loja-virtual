package br.com.serratec.lojavirtual.model.produto_pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.serratec.lojavirtual.model.cliente.Cliente;

@Entity
@Table(name = "pedidos")
public class Pedidos {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private Integer numeroDoPedido;
	private Double valorTotalDoPedido;
	private LocalDate dataDoPedido;
	private String status;
	// private Long cliente_id;

	@ManyToMany
	@JoinTable(name = "pedido_produto", joinColumns = @JoinColumn(name = "pedidoId"), inverseJoinColumns = @JoinColumn(name = "produtoId"))
	private List<Produto> listaDeProdutos = new ArrayList<Produto>();

	@ManyToOne()
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;


	public Pedidos() {
	}

	public Pedidos(Integer numeroDoPedido, ArrayList<Produto> listaDeProdutos, Double valorTotalDoPedido,
			LocalDate dataDoPedido, String status, Long cliente_id) {

		this.numeroDoPedido = numeroDoPedido;
		this.listaDeProdutos = listaDeProdutos;
		this.valorTotalDoPedido = valorTotalDoPedido;
		this.dataDoPedido = dataDoPedido;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}

	public Double getValorTotalDoPedido() {
		return valorTotalDoPedido;
	}

	public void setValorTotalDoPedido(Double valorTotalDoPedido) {
		this.valorTotalDoPedido = valorTotalDoPedido;
	}

	public LocalDate getDataDoPedido() {
		return dataDoPedido;
	}

	public void setDataDoPedido(LocalDate dataDoPedido) {
		this.dataDoPedido = dataDoPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
