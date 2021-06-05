package br.com.serratec.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.lojavirtual.model.produto.Categoria;
import br.com.serratec.lojavirtual.service.CategoriaService;

@RestController
@RequestMapping(value = "/api/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService _categoriaService;
	
	@GetMapping
	public List<Categoria> obter(){
		return this._categoriaService.obter();
	}
	
	@PostMapping
	public void adicionar(@RequestBody Categoria categoria) {
		this._categoriaService.adicionar(categoria);
	}
	
	@PutMapping(value = "/{id}")
	public void atualizar(@PathVariable(value = "id")Long id, @RequestBody Categoria categoria) {
		this._categoriaService.atualizar(id, categoria);
	}
	
	@DeleteMapping (value = "/{id}")
	public void apagar(@PathVariable(value = "id") Long id) {
		this._categoriaService.apagar(id);
	}
}
