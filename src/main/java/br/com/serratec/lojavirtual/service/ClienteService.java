package br.com.serratec.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.lojavirtual.exception.ResourceBadRequestException;
import br.com.serratec.lojavirtual.model.cliente.Cliente;
import br.com.serratec.lojavirtual.repository.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository _repositorioCliente;
	

	
	public List<Cliente> obter(){
		return this._repositorioCliente.findAll();
	}
	
	
	public Cliente adicionar( Cliente cliente) {

		if(!cliente.validoParaCadastro()){
			throw new ResourceBadRequestException("Campos obrigatórios ;-;");
		}
		return this._repositorioCliente.save(cliente);
	}
	

	public Cliente atualizar(Long id, Cliente cliente) {
		cliente.setId(id);
		
		return this._repositorioCliente.save(cliente);

	}

	

	public void apagar(Long id) {
		this._repositorioCliente.deleteById(id);
	}
	



}
