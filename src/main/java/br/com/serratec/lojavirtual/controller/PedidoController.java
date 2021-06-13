package br.com.serratec.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.lojavirtual.model.produto_pedido.Pedidos;
import br.com.serratec.lojavirtual.service.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService _servicePedidos;

    @ApiOperation("Obter todos os pedidos")
    @GetMapping
    public ResponseEntity<List<Pedidos>> obterTodos(){
        return new ResponseEntity<>(_servicePedidos.obterTodos(), HttpStatus.OK);
    }


    @ApiOperation("Criar um novo pedido")
    @PostMapping("/{clienteId}")
    public ResponseEntity<Pedidos> criarPedidos(@RequestBody PedidoRequest pedidoRequest, @PathVariable(value = "clienteId") Long clienteId){
        return new ResponseEntity<>(_servicePedidos.adicionarProdutos(pedidoRequest, clienteId), HttpStatus.CREATED);
    }

    // @ApiOperation("Editar um pedido")
    // @PutMapping("/{id}")
    // public ResponseEntity<Pedidos> editarPedidos(@PathVariable(value = "id")Long id, @RequestBody PedidoRequest pedidoRequest){
    //      _servicePedidos.atualizar(id, pedidoRequest);
    // }

    @ApiOperation("Deletar um pedido")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable(value = "id") Long id){
        _servicePedidos.deletar(id);
    }

}
