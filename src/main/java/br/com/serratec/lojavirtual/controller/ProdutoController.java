package br.com.serratec.lojavirtual.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Produto>> obterTodos(){
		
		return new ResponseEntity<>(this._produtoService.obterTodos(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna uma lista de produtos por nome")
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<List<Produto>> obterPorNome(@PathVariable(value = "nome") String nome) {
		return new ResponseEntity<>(this._produtoService.obterPorNome(nome), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna uma Lista de produtos por id")
	@GetMapping(value = "/idcategoria/{idcategoria}")
	public ResponseEntity<List<Produto>> obterPorCategoria(@PathVariable(value = "idcategoria") Long idcategoria){

		return new ResponseEntity<>(_produtoService.obterPorCategoria(idcategoria), HttpStatus.OK);
	}

	@ApiOperation(value = "Obter Produto por id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produto>> obterPorId(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(_produtoService.obterPorId(id), HttpStatus.OK);
	}

	//#endregion

	//#region Post

	@ApiOperation(value = "Cadastra um produto")
	@PostMapping
	public ResponseEntity<Produto> adicionar(@RequestBody Produto produto) {
		
		return new ResponseEntity<>(this._produtoService.adicionar(produto), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Cadastra um produto com uma categoria já existente")
	@PostMapping("/{categoriaId}")
	public ResponseEntity<Produto> adicionarComCategoriaExistente(@RequestBody Produto produto, @PathVariable(value = "categoriaId") Long categoriaId) {
		
		return new ResponseEntity<>(this._produtoService.adicionarComCategoriaExistente(produto, categoriaId), HttpStatus.CREATED);
	}

	//#endregion

	//#region Put

	@ApiOperation(value = "Atualiza um produto já existente")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable(value = "id")Long id, @RequestBody Produto produto) {
		
		return new ResponseEntity<>(this._produtoService.atualizar(id, produto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Atualiza produto com categoria")
	@PutMapping(value = "/{id}/categoria/{categoriaId}")
	public ResponseEntity<Produto> atualizar(@PathVariable(value = "id")Long id, @RequestBody Produto produto, @PathVariable(value = "categoriaId") Long categoriaId) {
		
		return new ResponseEntity<>(this._produtoService.atualizarCategoriaDoProduto(id, produto, categoriaId), HttpStatus.OK);
	}

	//#endregion

	@ApiOperation(value = "Deleta um produto exitente")
	@DeleteMapping (value = "/{id}")
	public void apagar(@PathVariable(value = "id") Long id) {
		
		this._produtoService.apagar(id);
	}

}
