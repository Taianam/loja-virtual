package br.com.serratec.lojavirtual.repository;

	import java.util.Optional;

	import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.lojavirtual.model.cliente.Cliente;

 

	public interface ClienteRepository extends JpaRepository<Cliente, Long> {
		Optional<Cliente> findById(Long id);
		Optional<Cliente>  findByEmail(String email);
	}
		
		
		


