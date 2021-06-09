package br.com.serratec.lojavirtual.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.lojavirtual.exception.ResourceNotFoundException;
import br.com.serratec.lojavirtual.model.produto.Categoria;
import br.com.serratec.lojavirtual.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository _repositorioCategoria;

	public List<Categoria> obter() {
		return this._repositorioCategoria.findAll();
	}

	public Categoria adicionar(Categoria categoria) {
		categoria.setId(null);
		verificarSeCategoriaEValida(categoria);
		
		return this._repositorioCategoria.save(categoria);
	}
	
	public Categoria atualizar(Long id, Categoria categoria) {
		verificarSeCategoriaExiste(id);
		categoria.setId(id);
		
		return this._repositorioCategoria.save(categoria);

	}

	public void apagar(Long id) {
		verificarSeCategoriaExiste(id);

		this._repositorioCategoria.deleteById(id);
	}

	// /!\ Alterar exception /!\
	private void verificarSeCategoriaEValida(Categoria categoria) {

		if (categoria.getNome() == null || categoria.getNome() == "" || categoria.getDescricao() == null
				|| categoria.getDescricao() == "") {
			throw new RuntimeException();
		}
	}

	// /!\ Alterar exception /!\
	private void verificarSeCategoriaExiste(Long id) {
		Optional<Categoria> categoria = this._repositorioCategoria.findById(id);

		if (categoria.isEmpty()) {
			throw new ResourceNotFoundException("Categoria não encontrada :(");
		}
	}

}
