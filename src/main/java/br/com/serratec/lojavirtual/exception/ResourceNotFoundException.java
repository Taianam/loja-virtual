package br.com.serratec.lojavirtual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException() {
		super("Recurso não encontrado");
	}
	
	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}
    
}
