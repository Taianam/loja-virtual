package br.com.serratec.lojavirtual.controller;

import java.time.LocalDate;
import java.util.List;

public class PedidoRequest {
    
    private Integer numeroDoPedido;
	private LocalDate dataDoPedido;
	private Boolean status;

    private List<Long> produtosId;

    public Integer getNumeroDoPedido() {
        return numeroDoPedido;
    }

    public void setNumeroDoPedido(Integer numeroDoPedido) {
        this.numeroDoPedido = numeroDoPedido;
    }

    public LocalDate getDataDoPedido() {
        return dataDoPedido;
    }

    public void setDataDoPedido(LocalDate dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Long> getProdutosId() {
        return produtosId;
    }

    public void setProdutosId(List<Long> produtosId) {
        this.produtosId = produtosId;
    }

    
   
}
