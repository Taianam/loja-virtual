package br.com.serratec.lojavirtual.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.lojavirtual.exception.ResourceBadRequestException;
import br.com.serratec.lojavirtual.exception.ResourceNotFoundException;
import br.com.serratec.lojavirtual.model.produto_pedido.Categoria;
import br.com.serratec.lojavirtual.model.produto_pedido.Produto;
import br.com.serratec.lojavirtual.repository.CategoriaRepository;
import br.com.serratec.lojavirtual.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository _repositorioProduto;

	@Autowired
	CategoriaRepository _repositorioCategoria;

	public List<Produto> obterTodos() {
		return this._repositorioProduto.findAll();
	}

	public Optional<Produto> obterPorId(Long id) {
		Optional<Produto> Produto = this._repositorioProduto.findById(id);
		
		if(Produto.isEmpty()) {
			
			throw new ResourceNotFoundException("Não foi encontrado nenhuma Produto para o id: " + id);
		}
		return Produto;
	}

	public List<Produto> obterPorNome(String nome) {
		List<Produto> produtos = _repositorioProduto.findByNomeContainingIgnoreCase(nome);
		if (produtos.isEmpty()) {
			throw new ResourceNotFoundException("Produto não existente :(");
		}
		return produtos;
	}

	public Produto adicionar(Produto produto) {
		produto.setId(null);
		verificarValorProduto(produto);

		return this._repositorioProduto.save(produto);
	}

	
	public Produto adicionarComCategoriaExistente(Produto produto, Long categoriaId) {
					
		Optional<Categoria> categoria = _repositorioCategoria.findById(categoriaId);
		
		verificarValorProduto(produto);

		if (categoria.isPresent()) {
			produto.setCategoriaId(categoria.get());
		} else {
			throw new ResourceBadRequestException("Categoria invalida.");
		}

		return this._repositorioProduto.save(produto);
		}

	public Produto atualizar(Long id, Produto produto) {

		verificarSeProdutoExiste(id);
		verificarValorProduto(produto);
		produto.setId(id);

		return this._repositorioProduto.save(produto);

	}

	public Produto atualizarCategoriaDoProduto(Long id, Produto produto, Long categoriaId) {

		verificarSeProdutoExiste(id);

		produto.setId(id);
		
		Optional<Categoria> categoria = _repositorioCategoria.findById(categoriaId);

		if(categoria.isPresent()){
			produto.setCategoriaId(categoria.get());
		} else {
			return null;
		}

		return this._repositorioProduto.save(produto);

	}

	public void apagar(Long id) {
		verificarSeProdutoExiste(id);

		this._repositorioProduto.deleteById(id);
	}

	//#region Métodos

	private void verificarValorProduto(Produto produto){
		if(!(produto.getPreco() * -1 < 0)){
			throw new ResourceBadRequestException("Não é possivel cadastrar um produto com valor abaixo de 0.0");
			
		}
	}

	private void verificarSeProdutoExiste(Long id) {
		Optional<Produto> produto = this._repositorioProduto.findById(id);

		if (produto.isEmpty()) {
			throw new ResourceNotFoundException("Produto não existente :(");
		}
	}

	//#endregion
	
}
