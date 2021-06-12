package br.com.serratec.lojavirtual.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.serratec.lojavirtual.model.produto_pedido.Produto;
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
	
	//#region Get

	@ApiOperation(value = "Retorna uma lista de produtos cadastrados")
	@GetMapping
	public List<Produto> obter(){
		
		return this._produtoService.obter();
	}
	
<<<<<<< HEAD
	@ApiOperation(value = "Retorna uma lista de produtos por nome")
	@GetMapping(value = "/nome/{nome}")
	public List<Produto> obter(@PathVariable(value = "nome") String nome) {
		return this._produtoService.obter(nome);
	}

	
=======
	@ApiOperation(value = "Obter Produto por id")
	@GetMapping("/{id}")
	public Optional<Produto> obterPorId(@PathVariable(value = "id") Long id){
		return _produtoService.obterPorId(id);
	}

	//#endregion

	//#region Post

>>>>>>> 260e690d62dc5768b793fa5144f5859bc02c8e15
	@ApiOperation(value = "Cadastra um produto")
	@PostMapping
	public Produto adicionar(@RequestBody Produto produto) {
		
		return this._produtoService.adicionar(produto);
	}

	@ApiOperation(value = "Cadastra um produto com uma categoria já existente")
	@PostMapping("/{categoriaId}")
	public Produto adicionarComCategoriaExistente(@RequestBody Produto produto, @PathVariable(value = "categoriaId") Long categoriaId) {
		
		return this._produtoService.adicionarComCategoriaExistente(produto, categoriaId);
	}

	//#endregion

	//#region Put

	@ApiOperation(value = "Atualiza um produto já existente")
	@PutMapping(value = "/{id}")
	public Produto atualizar(@PathVariable(value = "id")Long id, @RequestBody Produto produto) {
		
		return this._produtoService.atualizar(id, produto);
	}
	
	@ApiOperation(value = "Atualiza a categoria de um produto já existente")
	@PutMapping(value = "/{id}/categoria/{categoriaId}")
	public Produto atualizar(@PathVariable(value = "id")Long id, @RequestBody Produto produto, @PathVariable(value = "categoriaId") Long categoriaId) {
		
		return this._produtoService.atualizarCategoriaDoProduto(id, produto, categoriaId);
	}

	//#endregion

	@ApiOperation(value = "Deleta um produto exitente")
	@DeleteMapping (value = "/{id}")
	public void apagar(@PathVariable(value = "id") Long id) {
		
		this._produtoService.apagar(id);
	}

}
