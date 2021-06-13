//package br.com.serratec.lojavirtual.security;
//
//import java.util.Optional;
//import java.util.function.Supplier;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import br.com.serratec.token.model.Cliente;
//import br.com.serratec.token.repository.ClienteRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService{
//
//	@Autowired
//	private ClienteRepository clienteRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String email) {
//		Cliente cliente = getUser(() -> clienteRepository.findByEmail(email));
//		return cliente;
//	}
//	
//	public UserDetails pegarUsuarioPorId(Long id) {
//		Cliente cliente = getUser(() -> clienteRepository.findById(id));
//		return cliente;
//	}
//	
//	
//	private Cliente getUser(Supplier<Optional<Cliente>> supplier) {
//		return supplier.get().orElseThrow(() -> 
//				new UsernameNotFoundException("Usuário não encontrado"));
//	}
//
//}
