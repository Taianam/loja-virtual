package br.com.serratec.lojavirtual.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.lojavirtual.dto.LoginRequest;
import br.com.serratec.lojavirtual.model.cliente.Cliente;
import br.com.serratec.lojavirtual.service.ClienteService;



@RestController
@RequestMapping("/api/recuperar")
public class RecuperarSenhaController {


	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public String enviarEmail(@RequestBody Map<String, String> email) {
		return clienteService.enviarEmail(email);
	}
	
	
	@PatchMapping("email/{senha}")
	public Cliente recuperarSenha(@RequestBody LoginRequest novo, @PathVariable(value = "senha") String senhaEmail) {
		return clienteService.recuperarSenha(senhaEmail, novo);
	}
}
