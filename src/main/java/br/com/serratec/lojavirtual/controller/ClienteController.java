package br.com.serratec.lojavirtual.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.lojavirtual.dto.AtualizarCliente;
import br.com.serratec.lojavirtual.dto.LoginRequest;
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
	public ResponseEntity<List<Cliente>> obterTodos() {
		return new ResponseEntity<>(this._clienteService.obterTodos(), HttpStatus.OK);
	}

	@ApiOperation(value = "Obter um cliente pelo seu id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cliente>> obterPorId(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(_clienteService.obterPorId(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna uma lista de clientes por nome")
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<List<Cliente>> obterPorNome(@PathVariable(value = "nome") String nome) {
		return new ResponseEntity<>(this._clienteService.obterPorNome(nome), HttpStatus.OK);
	}

	@ApiOperation(value = "Cadastra uma cliente")
	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(this._clienteService.cadastrar(cliente), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Atualiza alguns campos do cliente já existente, verificando seu email e senha")
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable(value = "id") Long id,
			@RequestBody AtualizarCliente atualizar) {
		return new ResponseEntity<>(this._clienteService.atualizar(id, atualizar), HttpStatus.OK);
	}

	@ApiOperation(value = "Deleta um cliente exitente, verificando seu email e senha")
	@DeleteMapping(value = "/{id}")
	public String deletar(@PathVariable(value = "id") Long id, @RequestBody LoginRequest login) {
		return this._clienteService.deletar(id, login);
	}
}