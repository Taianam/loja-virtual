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

import br.com.serratec.lojavirtual.model.produto_pedido.Categoria;
import br.com.serratec.lojavirtual.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value = "API REST Loja-Vitual de HQs")
@RestController
@RequestMapping(value = "/api/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService _categoriaService;

	@ApiOperation(value = "Retorna uma lista de categorias cadastradas")
	@GetMapping
	public ResponseEntity<List<Categoria>> obterTodos() {
		return new ResponseEntity<>(this._categoriaService.obterTodos(), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna uma lista de categorias por nome")
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<List<Categoria>> obterPorNome(@PathVariable(value = "nome") String nome) {

		return new ResponseEntity<>(this._categoriaService.obterPorNome(nome), HttpStatus.OK);
	}
		
	@ApiOperation(value = "Obter Categoria por id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Categoria>> obterPorId(@PathVariable(value = "id") Long id){
		return new ResponseEntity<>(_categoriaService.obterPorId(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Cadastra uma categoria")
	@PostMapping
	public ResponseEntity<Categoria> adicionar(@RequestBody Categoria categoria) {
		return new ResponseEntity<>(this._categoriaService.adicionar(categoria), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Atualiza uma categoria já existente")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable(value = "id") Long id, @RequestBody Categoria categoria) {
		return new ResponseEntity<>(this._categoriaService.atualizar(id, categoria), HttpStatus.OK);
	}

	@ApiOperation(value = "Deleta uma categotia exitente")
	@DeleteMapping(value = "/{id}")
	public void deletar(@PathVariable(value = "id") Long id) {
		this._categoriaService.deletar(id);
	}
}
