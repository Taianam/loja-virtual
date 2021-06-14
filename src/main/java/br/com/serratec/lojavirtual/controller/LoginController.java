package br.com.serratec.lojavirtual.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.lojavirtual.dto.LoginRequest;
import br.com.serratec.lojavirtual.dto.LoginResponse;
import br.com.serratec.lojavirtual.service.ClienteService;


@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private ClienteService clienteService;
		
	@PostMapping
	public LoginResponse login (@RequestBody LoginRequest request) {		
		return clienteService.logar(request.getEmail(), request.getSenha());
	}
	

}
