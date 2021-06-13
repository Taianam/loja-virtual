package br.com.serratec.lojavirtual.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.lojavirtual.model.produto_pedido.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Optional<Categoria> findById(Long id);
	List<Categoria> findByNomeContainingIgnoreCase(String nome);
}
