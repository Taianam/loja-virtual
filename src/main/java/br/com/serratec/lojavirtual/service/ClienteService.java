package br.com.serratec.lojavirtual.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.serratec.lojavirtual.exception.ResourceBadRequestException;
import br.com.serratec.lojavirtual.exception.ResourceNotFoundException;
import br.com.serratec.lojavirtual.model.cliente.Cliente;
import br.com.serratec.lojavirtual.model.cliente.Endereco;
import br.com.serratec.lojavirtual.model.cliente.EnderecoViaCep;
import br.com.serratec.lojavirtual.model.email.Mailler;
import br.com.serratec.lojavirtual.model.email.MenssagemEmail;
import br.com.serratec.lojavirtual.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository _repositorioCliente;

	@Autowired
	private Mailler mailler;

	@Autowired
	private CepService servicoCep;

	public List<Cliente> obter() {
		return this._repositorioCliente.findAll();
	}

	public Cliente adicionar(Cliente cliente) {
		
		cliente.setId(null);
		
		ligarViaCepComEdereco(cliente.getEndereco());
		
		validarCPF(cliente.getCpf());
		

		this._repositorioCliente.save(cliente);
		
		var mensagem = "<!DOCTYPE html><html lang=pt-BR><head><meta charset=UTF-8><meta http-equiv=X-UA-Compatible content=\\\"IE=edge\\\"><meta name=viewport content=\\\"width=device-width, initial-scale=1.0\\\"><title>Email</title><body style=background-color:#8abee6><div style=color:white;text-align:center><h1>Bem vindo, %s !</h1><h2>Parabéns! Seu cadastro foi realizado com sucesso!</h2></div><div style=text-align:center><img style=width:400px src=https://www.ecommerceworld.com.br/wp-content/uploads/2015/12/loja-virtual-e-commerce.png alt=eComerce></div><h2 style=color:white;text-align:center>A Familia Dev-HQs, agradece a sua preferencia!<br>Boas Compras!</h2>";
		mensagem = String.format(mensagem, cliente.getNome());
		var email = new MenssagemEmail("Cadastro realizado com sucesso!", mensagem, cliente.getEmail(),
				Arrays.asList(cliente.getEmail()));
		mailler.enviar(email);

		return cliente;
	}

	public Cliente atualizar(Long id, Cliente cliente) {

		verificarSeClienteExiste(id);
		cliente.setId(id);	
		ligarViaCepComEdereco(cliente.getEndereco());
		validarCPF(cliente.getCpf());
	
		return this._repositorioCliente.save(cliente);
	}

	
	public void apagar(Long id) {

		verificarSeClienteExiste(id);
		this._repositorioCliente.deleteById(id);
	}

	// METODOS DE VERIFICAÇÃO/VALIDALÇAO
	private void verificarSeClienteExiste(Long id) {
		Optional<Cliente> cliente = this._repositorioCliente.findById(id);

		if (cliente.isEmpty()) {
			throw new ResourceNotFoundException("Cliente não encontrada :(");
		}
	}
	
	public void validarCPF(String cpf){ 
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
	
	public void validarEndereco(Endereco endereco) {
	//CONCERTAR	
//		 if("".equals()){
//		    	throw new ResourceBadRequestException("CEP invalido!");
//		 } 	
	}
}
	
