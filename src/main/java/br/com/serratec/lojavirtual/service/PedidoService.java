package br.com.serratec.lojavirtual.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

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
    	Random random = new Random();
    	Integer numeroPedido = random.nextInt(999999);

        Pedidos pedido = new Pedidos();
        Optional<Cliente> cliente = _clienteRepository.findById(clienteId);

        if(cliente.isEmpty()){
        	throw new ResourceNotFoundException("Cliente não cadastrado!");
        }
        pedido.setCliente(cliente.get());
        pedido.setDataDoPedido(pedidoRequest.getDataDoPedido());
        pedido.setNumeroDoPedido(numeroPedido);
        if(pedidoRequest.getPedidoFinalizado().equalsIgnoreCase("sim")){
            pedido.setPedidoFinalizado(true);
        } else{
            pedido.setPedidoFinalizado(false);
        }
        
        pedidoRequest.getProdutosId().forEach(id -> {
        	Optional<Produto> p = _produtoRepository.findById(id);
        	if(p.isPresent()){
        		pedido.getListaDeProdutos().add(p.get());
        		pedido.verificarEstoque();
        	}
        });
        
        if(pedido.getListaDeProdutos().size() == 0) {
        	throw new ResourceBadRequestException("Impossivel finalizar o pedido, produto esgotado no estoque :(");
        }
        pedido.calcularValorTotal();
        
        enviarEmail(pedido.getPedidoFinalizado(), pedido);
        
      
        
        return _pedidoRepository.save(pedido);
    }

    public Pedidos atualizar(PedidoRequest pedidoRequest,Long id){

    	Pedidos pedido = verificarSePedidoExiste(id).get();
    	if(pedido.getPedidoFinalizado().equals(true)) {
    		throw new ResourceAccessException("Seu pedido já foi finalizado, infelizemente não é possivel atualizar!");
    	}
        pedido.setId(id);
        pedido.setDataDoPedido(pedidoRequest.getDataDoPedido());
        pedido.setNumeroDoPedido(pedidoRequest.getNumeroDoPedido());
        if(pedidoRequest.getPedidoFinalizado().equalsIgnoreCase("sim")){
            pedido.setPedidoFinalizado(true);
        } else{
            pedido.setPedidoFinalizado(false);
        }
        
        pedidoRequest.getProdutosId().forEach(idProduto -> {
        	Optional<Produto> p = _produtoRepository.findById(idProduto);
        	if(p.isPresent()){
        		pedido.getListaDeProdutos().add(p.get());
        		pedido.verificarEstoque();
        	}
        });
        if(pedido.getListaDeProdutos().size() == 0) {
        	throw new ResourceBadRequestException("Impossivel finalizar o pedido, produto esgotado no estoque :(");
        }
        pedido.calcularValorTotal();
       
        enviarEmail(pedido.getPedidoFinalizado(), pedido);
        
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

    public void enviarEmail(Boolean pedidoFinalizado, Pedidos pedido){
    	String imagem = null;
    	LocalDate dataEntrega = pedido.getDataDoPedido().plusDays(7);
    	if (pedidoFinalizado == true) {
    	   for (Produto produto : pedido.getListaDeProdutos()) {
			imagem += produto.getImagem();
    	   }
    		var mensagem ="<!doctypehtml><html lang=en><meta charset=UTF-8><meta content=\\\"IE"
    				+ "=edge\\\"http-equiv=X-UA-Compatible><meta content=\\\"width=device-width,"
    				+ "initial-scale=1\\\"name=viewport><title>Compras</title><body style=backgr"
    				+ "ound-color:#8abee6;color:#fff;text-align:center><h2 style=text-align:cente"
    				+ "r>Ola, %s, compra realizada com sucesso!</h2><h3 style=text-align:center>"
    				+ "A equipe Dev-HQs, agradece pela sua confiança!</h3><h4>Suas compras!</h4>"
    				+ "<div>%s</div><h2>Data do pedido:%s</h2><h2>Data de entrega: %s</h2>"
    				+ "<h2>Valor Total da sua compra: %s</h2>";
    				mensagem = String.format(mensagem, pedido.getCliente().getNome(), imagem, pedido.getDataDoPedido(), dataEntrega,pedido.getValorTotalDoPedido());
    				
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
