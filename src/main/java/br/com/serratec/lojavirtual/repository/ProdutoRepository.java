package br.com.serratec.lojavirtual.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.lojavirtual.model.produto_pedido.Produto;

public interface ProdutoRepository  extends JpaRepository<Produto, Long>{

	Optional<Produto> findById(Long id);
	List<Produto> findByNomeContaining(String nome);
}
