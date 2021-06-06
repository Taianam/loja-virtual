package br.com.serratec.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.serratec.lojavirtual.model.produto.Produto;
import br.com.serratec.lojavirtual.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value = "API REST Loja-Vitual de HQs")
@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoService _produtoService;
	
	
	@ApiOperation(value = "Retorna uma lista de produtos cadastrados")
	@GetMapping
	public List<Produto> obter(){
		return this._produtoService.obter();
	}
	
	@ApiOperation(value = "Cadastra um produto")
	@PostMapping
	public Produto adicionar(@RequestBody Produto produto) {
		return this._produtoService.adicionar(produto);
	}
	
	@ApiOperation(value = "Atualiza um produto já existente")
	@PutMapping(value = "/{id}")
	public Produto atualizar(@PathVariable(value = "id")Long id, @RequestBody Produto produto) {
		return this._produtoService.atualizar(id, produto);
	}
	
	@ApiOperation(value = "Deleta um produto exitente")
	@DeleteMapping (value = "/{id}")
	public void apagar(@PathVariable(value = "id") Long id) {
		this._produtoService.apagar(id);
	}

}