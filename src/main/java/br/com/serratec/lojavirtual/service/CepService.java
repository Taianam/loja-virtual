package br.com.serratec.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.serratec.lojavirtual.model.cliente.EnderecoViaCep;
import reactor.core.publisher.Mono;

@Service
public class CepService {

	@Autowired
	private WebClient cepWebClient;
	
	public EnderecoViaCep obterEnderecoPorCep(String cep) {
		
		
		try {
			
			Mono<EnderecoViaCep> monoEndereco = this.cepWebClient
				.method(HttpMethod.GET) 
				.uri("http://viacep.com.br/ws/{cep}/json", cep)
				.retrieve() 
				.bodyToMono(EnderecoViaCep.class); 
			var endereco = monoEndereco.block(); 
			
			return endereco;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new EnderecoViaCep();
		}

	}
	
	
	
}