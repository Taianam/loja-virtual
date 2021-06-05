package br.com.serratec.lojavirtual.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.lojavirtual.model.produto.Categoria;
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

	// /!\ Alterar exception /!\
	private void verificarSeCategoriaEValida(Categoria categoria) {

		if (categoria.getNome() == null || categoria.getNome() == "" || categoria.getDescricao() == null
				|| categoria.getDescricao() == "") {
			throw new RuntimeException();
		}
	}

	// /!\ Alterar exception /!\
	private void VerificarSeCategoriaExiste(Long id) {
		Optional<Categoria> categoria = this._repositorioCategoria.findById(id);

		if (categoria.isEmpty()) {
			throw new RuntimeException();
		}
	}

}
