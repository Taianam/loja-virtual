package br.com.serratec.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.lojavirtual.model.Categoria;
import br.com.serratec.lojavirtual.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository _repositorioCategoria;

	public List<Categoria> obter() {
		return this._repositorioCategoria.findAll();
	}

	public void adicionar(Categoria categoria) {
		verificarSeCategoriaEValida(categoria);
		
		this._repositorioCategoria.save(categoria);
	}
	
	public void atualizar(Long id, Categoria categoria) {
		VerificarSeCategoriaExiste(id);
		
		this._repositorioCategoria.save(categoria);
	}
	
	public void apagar(Long id) {
		VerificarSeCategoriaExiste(id);
		
		this._repositorioCategoria.deleteById(id);
	}
	
	// /!\ INCOMPLETO /!\
	private boolean verificarSeCategoriaEValida(Categoria categoria) {
	//  /!\ FAZER VALIDAÇÂO DA CATEGORIA /!\
		return true;
	}
	
	// /!\ INCOMPLETO /!\
	private boolean VerificarSeCategoriaExiste(Long id) {
	// /!\ VERIFICAR SE A CATEGORIA EXISTE /!\
		return true;
	}

}
