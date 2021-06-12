package br.com.serratec.lojavirtual.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.lojavirtual.model.produto_pedido.Pedidos;

public interface PedidoRepository extends JpaRepository<Pedidos, Long>{
    
    Optional<Pedidos> findById(Long id);
}
