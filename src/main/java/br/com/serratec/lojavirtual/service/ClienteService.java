package br.com.serratec.lojavirtual.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.serratec.lojavirtual.dto.AtualizarCliente;
import br.com.serratec.lojavirtual.dto.LoginRequest;
import br.com.serratec.lojavirtual.dto.LoginResponse;
import br.com.serratec.lojavirtual.exception.ResourceBadRequestException;
import br.com.serratec.lojavirtual.exception.ResourceNotFoundException;
import br.com.serratec.lojavirtual.model.cliente.Cliente;
import br.com.serratec.lojavirtual.model.cliente.Endereco;
import br.com.serratec.lojavirtual.model.cliente.EnderecoViaCep;
import br.com.serratec.lojavirtual.model.email.Mailler;
import br.com.serratec.lojavirtual.model.email.MenssagemEmail;
import br.com.serratec.lojavirtual.repository.ClienteRepository;
import br.com.serratec.lojavirtual.security.JWTService;

@Service
public class ClienteService {
	private static final String headerPrefix = "Bearer ";
	
	
	@Autowired
	private JWTService jwtService;
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEnconder;

	@Autowired
	private Mailler mailler;

	@Autowired
	private ClienteRepository _repositorioCliente;


	@Autowired
	private CepService servicoCep;

	public List<Cliente> obterTodos() {
		return this._repositorioCliente.findAll();
	}

	public Optional<Cliente> obterPorId(Long id) {
		Optional<Cliente> Cliente = this._repositorioCliente.findById(id);
		
		if(Cliente.isEmpty()) {
			
			throw new ResourceNotFoundException("Não foi encontrado nenhum Cliente para o id: " + id);
		}
		return Cliente;
	}


	public List<Cliente> obterPorNome(String nome) {
		List<Cliente> clientes = _repositorioCliente.findByNomeContainingIgnoreCase(nome);
		if (clientes.isEmpty()) {
			throw new ResourceNotFoundException("Cliente não encontrada :(");
		}
		return clientes;
	}


	
	public Cliente cadastrar(Cliente cliente){
		
		cliente.setId(null);
		verificarSeEmailExiste(cliente.getEmail());
		validarCPF(cliente.getCpf());
		ligarViaCepComEdereco(cliente.getEndereco());

		try {
			this._repositorioCliente.save(cliente);
		} catch (Exception e) {

			throw new ResourceBadRequestException("Campo obrigatorio :(");

		}
		String senha = passwordEnconder.encode(cliente.getSenha());
		cliente.setSenha(senha);
		
		var mensagem = "<!DOCTYPE html><html lang=pt-BR><head><meta charset=UTF-8><meta http-"
		+ "equiv=X-UA-Compatible content=\"IE=edge\"><meta name=viewport content="
		+ "\"width=device-width, initial-scale=1.0\"><title>Email</title><body "
		+ "style=background-color:#8abee6><div style=color:white;text-align:center><h1>Bem"
		+ " vindo, %s !</h1><h2>Parabéns! Seu cadastro foi realizado com sucesso"
		+ "!</h2></div><div style=text-align:center><img style=width:400px src=https://www/."
		+ "ecommerceworld.com.br/wp-content/uploads/2015/12/loja-virtual-e-commerce.png"
		+ " alt=eComerce></div><h2 style=color:white;text-align:center>A Familia Dev-HQs, "
		+ "agradece a sua preferencia!<br>Boas Compras!</h2>";		
		mensagem = String.format(mensagem, cliente.getNome());
		
		var email = new MenssagemEmail("Cadastro realizado com sucesso!", mensagem, Arrays.asList(cliente.getEmail()));
		mailler.enviar(email);

		return cliente;
	}

	public Cliente atualizar(Long id, AtualizarCliente atualizar) {
	
		Cliente clienteAtualizado = verificarSeClienteExiste(id).get();
		
		if (!atualizar.getCpf().equals("")){
			throw new ResourceBadRequestException("Infelizmente não é possivel auterar seu cpf");
		}else if (!atualizar.getSenha().equals(clienteAtualizado.getSenha())) {
			throw new ResourceNotFoundException("Usuario não encontrado!");
		}
		clienteAtualizado.setId(id);
		ligarViaCepComEdereco(atualizar.getEndereco());
		clienteAtualizado.setDataDeNascimento(atualizar.getDataDeNascimento());
		clienteAtualizado.setEmail(atualizar.getNovoEmail());
		clienteAtualizado.setSenha(passwordEnconder.encode(atualizar.getNovaSenha()));
		clienteAtualizado.setNome(atualizar.getNome());
		clienteAtualizado.setTelefone(atualizar.getTelefone());
		return clienteAtualizado;	
	}		
	
	public String  deletar(Long id, LoginRequest login) {
		Cliente cliente = verificarSeClienteExiste(id).get();
		if(!login.getEmail().equals(cliente.getEmail()) &&  !login.getSenha().equals(cliente.getSenha())){
			throw new ResourceBadRequestException("Dados invalidos!");
		}
		this._repositorioCliente.deleteById(id);
		return "Cliente deletado";
	}
	
	// METODOS DE VERIFICAÇÃO/VALIDALÇAO
	private Optional<Cliente>  verificarSeClienteExiste(Long id) {
		Optional<Cliente> cliente = this._repositorioCliente.findById(id);

		if (cliente.isEmpty()) {
			throw new ResourceNotFoundException("Cliente não encontrada :(");
		}
		return cliente;
	}

	public void validarCPF(String cpf) {
		try {
			CPFValidator cpfValidado = new CPFValidator();
			cpfValidado.assertValid(cpf);

		} catch (InvalidStateException e) {

			throw new ResourceBadRequestException("CPF invalido!");

		}
	}

	public void ligarViaCepComEdereco(Endereco endereco) {
		EnderecoViaCep enderecoCompletado = servicoCep.obterEnderecoPorCep(endereco.getCep());
		endereco.setRua(enderecoCompletado.getLogradouro());
		endereco.setComplemento(enderecoCompletado.getComplemento());
		endereco.setBairro(enderecoCompletado.getBairro());
		endereco.setCidade(enderecoCompletado.getLocalidade());
		endereco.setEstado(enderecoCompletado.getUf());
	}

	private void verificarSeEmailExiste(String email) {
		Optional<Cliente> clienteEmail = this._repositorioCliente.findByEmail(email);
		if (!clienteEmail.isEmpty()) {
			throw new ResourceBadRequestException("Email, já cadastrado! :(");
		}
	}
	
	public String enviarEmail(Map<String, String> email) {


	Optional<Cliente> verificarEmail =_repositorioCliente.findByEmail(email.get("email"));
		
		if (verificarEmail.isEmpty()){
			
			return "Email invalido!";		
		}else {
			
			var mensagem = "<!doctypehtml><html lang=en><meta charset=UTF-8><meta content=\"IE=edge\"http-equiv=X-UA-Compatible>"
					+ "<meta content=\"width=device-width,initial-scale=1\"name=viewport><title>Email</title><body style=backgrou"
					+ "nd-color:#9ac2e2;text-align:center;color:#fff><h2>Esqueceu sua senha, %s ?</h2><h3>Não se preocupe, vamos"
					+ " ajudar você, estamos te mandando um link longo a baixo.<br>Se caso você clicar e não for redirecionado para "
					+ "a redefinição não se preocupe,<br>copie e cole o endereço no seu navegador, e tudo dara certo!</h3><a href=\""
					+ ">http://localhost:8080/api/recuperar/email/%s\"style=color:#fff target=_blanks>http://localhost:8080/api/"
					+ "recuperar/email/%s</a><div><img alt=\"\"src=https://www.almazena.com/wp-content/uploads/2015/04/cloud-"
					+ "security.png style=width:350px></div><h4>A família Dev-HQs agradece seu contato!</h4>";
			mensagem = String.format(mensagem, verificarEmail.get().getNome(), email.get("email"),email.get("email"));
			var enviar = new MenssagemEmail("Esqueceu sua senha?", mensagem, Arrays.asList(email.get("email")));
			mailler.enviar(enviar);
			return "Email enviado com sucesso, verefique sua caixa de email!";
		}
			
			
	}
	public Cliente recuperarSenha(String email, LoginRequest novo) {
		
		Optional<Cliente> verificarEmail= _repositorioCliente.findByEmail(email);
		
		if (verificarEmail.isPresent()) {
			var senhaNova = passwordEnconder.encode(novo.getSenha());
			verificarEmail.get().setSenha(senhaNova);
			Cliente clienteAtualizado = _repositorioCliente.save(verificarEmail.get());
			return clienteAtualizado;
		}

	return null;

}

	public LoginResponse logar(String email, String senha) {
		
		Authentication autenticacao = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(email, senha, Collections.emptyList()));
		
		SecurityContextHolder.getContext().setAuthentication(autenticacao);
		
		String token = headerPrefix + jwtService.gerarToken(autenticacao);
		
		var usuario = _repositorioCliente.findByEmail(email);
		
		return new LoginResponse(token, usuario.get());
	}
	
}
