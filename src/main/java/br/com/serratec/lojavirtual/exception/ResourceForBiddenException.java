package br.com.serratec.lojavirtual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ResourceForBiddenException extends Exception{

    public ResourceForBiddenException(){
        super("Gafanhoto você não tem permissão para fazer isso ;-;");
    }

    public ResourceForBiddenException(String mensagem){
        super(mensagem);
    }
}
