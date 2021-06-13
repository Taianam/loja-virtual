package br.com.serratec.lojavirtual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends RuntimeException{
    
	private static final long serialVersionUID = 1L;

	public ResourceBadRequestException(){
        super("Recurso fora dos parâmetros :(");
    }

    public ResourceBadRequestException(String mensagem){
        super(mensagem);
    }

}
