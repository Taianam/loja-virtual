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
<<<<<<< HEAD
	public List<Categoria> obter() {
		return this._categoriaService.obter();
	}

	@ApiOperation(value = "Retorna uma lista de categorias por nome")
	@GetMapping(value = "/nome/{nome}")
	public List<Categoria> obter(@PathVariable(value = "nome") String nome) {
		return this._categoriaService.obter(nome);
=======
	public List<Categoria> obterTodos(){
		return this._categoriaService.obterTodos();
	}
	
	@ApiOperation(value = "Obter Categoria por id")
	@GetMapping("/{id}")
	public Optional<Categoria> obterPorId(@PathVariable(value = "id") Long id){
		return _categoriaService.obterPorId(id);
>>>>>>> 260e690d62dc5768b793fa5144f5859bc02c8e15
	}

	@ApiOperation(value = "Cadastra uma categoria")
	@PostMapping
	public Categoria adicionar(@RequestBody Categoria categoria) {
		return this._categoriaService.adicionar(categoria);
	}

	@ApiOperation(value = "Atualiza uma categoria já existente")
	@PutMapping(value = "/{id}")
	public Categoria atualizar(@PathVariable(value = "id") Long id, @RequestBody Categoria categoria) {
		return this._categoriaService.atualizar(id, categoria);
	}

	@ApiOperation(value = "Deleta uma categotia exitente")
	@DeleteMapping(value = "/{id}")
	public void apagar(@PathVariable(value = "id") Long id) {
		this._categoriaService.apagar(id);
	}
}
