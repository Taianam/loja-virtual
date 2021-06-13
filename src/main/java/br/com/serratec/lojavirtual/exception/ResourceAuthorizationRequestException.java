package br.com.serratec.lojavirtual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class ResourceAuthorizationRequestException extends Exception{
    
    public ResourceAuthorizationRequestException(){
        super("Você não possui as credenciais de autenticação ;-;");
    }

    public ResourceAuthorizationRequestException(String mensagem){
        super(mensagem);
    }

}

