package br.com.serratec.lojavirtual.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.lojavirtual.exception.ResourceBadRequestException;
import br.com.serratec.lojavirtual.exception.ResourceNotFoundException;
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

		verificarSeClienteExiste(id);
		cliente.setId(id);
		return this._repositorioCliente.save(cliente);

	}

	public void apagar(Long id) {

		verificarSeClienteExiste(id);
		this._repositorioCliente.deleteById(id);
	}
	
	private void verificarSeClienteExiste(Long id){
		Optional<Cliente> cliente = this._repositorioCliente.findById(id);
	
		if (cliente.isEmpty()) {
			throw new ResourceNotFoundException("Cliente não encontrada :(");
		}
	}


}
