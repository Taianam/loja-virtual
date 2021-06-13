package br.com.serratec.lojavirtual.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.lojavirtual.dto.LoginRequest;
import br.com.serratec.lojavirtual.dto.PedidoRequest;
import br.com.serratec.lojavirtual.exception.ResourceBadRequestException;
import br.com.serratec.lojavirtual.exception.ResourceNotFoundException;
import br.com.serratec.lojavirtual.model.cliente.Cliente;
import br.com.serratec.lojavirtual.model.email.Mailler;
import br.com.serratec.lojavirtual.model.email.MenssagemEmail;
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
    
	@Autowired
	private Mailler mailler;

    public List<Pedidos> obterTodos(){
        return _pedidoRepository.findAll();
    }
    
    public Pedidos adicionarProdutos(PedidoRequest pedidoRequest, Long clienteId){

        Pedidos pedido = new Pedidos();
        Optional<Cliente> cliente = _clienteRepository.findById(clienteId);

        if(cliente.isEmpty()){
        	throw new ResourceNotFoundException("Cliente não cadastrado!");
        }
        pedido.setCliente(cliente.get());
        pedido.setDataDoPedido(pedidoRequest.getDataDoPedido());
        pedido.setNumeroDoPedido(pedidoRequest.getNumeroDoPedido());
        if(pedidoRequest.getStatus().equalsIgnoreCase("sim")){
            pedido.setStatus(true);
        } else{
            pedido.setStatus(false);
        }
        
        pedidoRequest.getProdutosId().forEach(id -> {
        	Optional<Produto> p = _produtoRepository.findById(id);
        	if(p.isPresent()){
        		pedido.getListaDeProdutos().add(p.get());
        		pedido.verificarEstoque();
        	}
        });
        pedido.calcularValorTotal();
        
        enviarEmail(pedido.getStatus(), pedido);
        
        return _pedidoRepository.save(pedido);
    }

    public Pedidos atualizar(PedidoRequest pedidoRequest,Long id){

    	Pedidos pedido = verificarSePedidoExiste(id).get();
        pedido.setId(id);
        pedido.setDataDoPedido(pedidoRequest.getDataDoPedido());
        pedido.setNumeroDoPedido(pedidoRequest.getNumeroDoPedido());
        if(pedidoRequest.getStatus().equalsIgnoreCase("sim")){
            pedido.setStatus(true);
        } else{
            pedido.setStatus(false);
        }
        
        pedidoRequest.getProdutosId().forEach(idProduto -> {
        	Optional<Produto> p = _produtoRepository.findById(idProduto);
        	if(p.isPresent()){
        		pedido.getListaDeProdutos().add(p.get());
        		pedido.verificarEstoque();
        	}
        });
        pedido.calcularValorTotal();
       
        enviarEmail(pedido.getStatus(), pedido);
        
        return _pedidoRepository.save(pedido);
    }

    public String deletar(Long id, LoginRequest login) {
		Cliente cliente = verificarSePedidoExiste(id).get().getCliente();
		if(!login.getEmail().equals(cliente.getEmail()) && !login.getSenha().equals(cliente.getSenha())){
			throw new ResourceBadRequestException("Dados invalidos!");
			}
        verificarSePedidoExiste(id);
        this._pedidoRepository.deleteById(id);
        return "Pedido Deletado";
    }

    public void enviarEmail(Boolean status, Pedidos pedido){
    	if (status == true) {
    		var mensagem = "<!DOCTYPE html><html lang=pt-BR><head><meta charset=UTF-8><meta http-"
    				+ "equiv=X-UA-Compatible content=\"IE=edge\"><meta name=viewport content="
    				+ "\"width=device-width, initial-scale=1.0\"><title>Email</title><body "
    				+ "style=background-color:#8abee6><div style=color:white;text-align:center><h1>Bem"
    				+ " vindo, %s !</h1><h2>Parabéns! Seu cadastro foi realizado com sucesso"
    				+ "!</h2></div><div style=text-align:center><img style=width:400px src=https://www/."
    				+ "ecommerceworld.com.br/wp-content/uploads/2015/12/loja-virtual-e-commerce.png"
    				+ " alt=eComerce></div><h2 style=color:white;text-align:center>A Familia Dev-HQs, "
    				+ "agradece a sua preferencia!<br>Boas Compras!</h2>";		
    				mensagem = String.format(mensagem, pedido);
    				
    				var email = new MenssagemEmail("Pedido Realizado com sucesso!", mensagem,Arrays.asList(pedido.getCliente().getEmail()));
    				mailler.enviar(email);
    	}
    }

    private Optional<Pedidos>  verificarSePedidoExiste(Long id) {
		Optional<Pedidos> pedido = this._pedidoRepository.findById(id);
		if (pedido.isEmpty()) {
			throw new ResourceNotFoundException("Pedido não encontrada :(");
		}
		return pedido;
    }
}
