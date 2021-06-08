package br.com.serratec.lojavirtual.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.lojavirtual.exception.ResourceBadRequestException;
import br.com.serratec.lojavirtual.exception.ResourceNotFoundException;
import br.com.serratec.lojavirtual.model.produto.Produto;
import br.com.serratec.lojavirtual.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository _repositorioProduto;

	public List<Produto> obter() {
		return this._repositorioProduto.findAll();
	}

	public Produto adicionar(Produto produto) {
		produto.setId(null);
		verificarSeProdutoEValido(produto);
		
		return this._repositorioProduto.save(produto);
	}
	
	public Produto atualizar(Long id, Produto produto) {
		VerificarSeProdutoExiste(id);
		produto.setId(id);
		
		return this._repositorioProduto.save(produto);

	}

	public void apagar(Long id) {
		VerificarSeProdutoExiste(id);

		this._repositorioProduto.deleteById(id);
	}

	// /!\ Alterar exception /!\
	private void verificarSeProdutoEValido(Produto produto) {

		if (produto.validarParaCadastro()) {
			throw new ResourceBadRequestException("Campos obrigatórios ;-;");
		}
	}

	// /!\ Alterar exception /!\
	private void VerificarSeProdutoExiste(Long id) {
		Optional<Produto> produto = this._repositorioProduto.findById(id);

		if (produto.isEmpty()) {
			throw new ResourceNotFoundException("Produto não existente :(");
		}
	}

}
