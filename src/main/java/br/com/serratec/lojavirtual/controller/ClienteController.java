package br.com.serratec.lojavirtual.controller;


	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.lojavirtual.model.cliente.Cliente;
import br.com.serratec.lojavirtual.service.ClienteService;
import io.swagger.annotations.Api;
	import io.swagger.annotations.ApiOperation;

	@CrossOrigin(origins = "*")
	@Api(value = "API REST Loja-Vitual de HQs")
	@RestController
	@RequestMapping(value = "/api/clientes")
	public class ClienteController {

		@Autowired
		ClienteService _clienteService;
		
		
		@ApiOperation(value = "Retorna uma lista de clientes cadastradas")
		@GetMapping
		public List<Cliente> obter(){
			return this._clienteService.obter();
		}
		
		@ApiOperation(value = "Cadastra uma cliente")
		@PostMapping
		public Cliente adicionar(@RequestBody Cliente cliente) {
			return this._clienteService.adicionar(cliente);
		}
		
		@ApiOperation(value = "Atualiza um cliente já existente")
		@PutMapping(value = "/{id}")
		public Cliente atualizar(@PathVariable(value = "id")Long id, @RequestBody Cliente cliente) {
			return this._clienteService.atualizar(id, cliente);
		}
		
		@ApiOperation(value = "Deleta um cliente exitente")
		@DeleteMapping (value = "/{id}")
		public void apagar(@PathVariable(value = "id") Long id) {
			this._clienteService.apagar(id);
		}
	}