package br.com.serratec.lojavirtual.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.lojavirtual.controller.PedidoRequest;
import br.com.serratec.lojavirtual.model.cliente.Cliente;
import br.com.serratec.lojavirtual.model.produto_pedido.Pedidos;
import br.com.serratec.lojavirtual.model.produto_pedido.Produto;
import br.com.serratec.lojavirtual.repository.ClienteRepository;
import br.com.serratec.lojavirtual.repository.PedidoRepository;
import br.com.serratec.lojavirtual.repository.ProdutoRepository;

@Service
public class PedidoService {
    

    @Autowired
    PedidoRepository _pedidoRepository;
    
    @Autowired
    ProdutoRepository _produtoRepository;

    @Autowired
    ClienteRepository _clienteRepository;

    public List<Pedidos> obterTodos(){
        return _pedidoRepository.findAll();
    }
    
    public Pedidos adicionarProdutos(PedidoRequest pedidoRequest, Long clienteId){

        Pedidos pedido = new Pedidos();

        Optional<Cliente> cliente = _clienteRepository.findById(clienteId);

        pedido.setDataDoPedido(pedidoRequest.getDataDoPedido());

        pedido.setNumeroDoPedido(pedidoRequest.getNumeroDoPedido());

        pedido.setStatus(pedidoRequest.getStatus());

        pedidoRequest.getProdutosId().forEach(id -> {
            Optional<Produto> p = _produtoRepository.findById(id);
            
            if(p.isPresent()){
                pedido.getListaDeProdutos().add(p.get());
                
            }
        
        }
        
        );

        if(cliente.isPresent()){
            pedido.setCliente(cliente.get());
 
        }  else {
             return null;
 
        }

        return _pedidoRepository.save(pedido);
    }

}
