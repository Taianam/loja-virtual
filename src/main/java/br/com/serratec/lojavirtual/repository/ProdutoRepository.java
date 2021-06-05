package br.com.serratec.lojavirtual.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.lojavirtual.model.produto.Categoria;
import br.com.serratec.lojavirtual.model.produto.Produto;

public interface ProdutoRepository  extends JpaRepository<Produto, Long>{

	Optional<Produto> findById(Long id);
}
