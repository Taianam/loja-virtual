package br.com.serratec.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.lojavirtual.model.produto.Categoria;



public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
