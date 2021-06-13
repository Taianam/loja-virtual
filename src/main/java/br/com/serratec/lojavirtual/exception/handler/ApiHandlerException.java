package br.com.serratec.lojavirtual.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.serratec.lojavirtual.error.ErrorMessage;
import br.com.serratec.lojavirtual.exception.ResourceAuthorizationRequestException;
import br.com.serratec.lojavirtual.exception.ResourceBadRequestException;
import br.com.serratec.lojavirtual.exception.ResourceForBiddenException;
import br.com.serratec.lojavirtual.exception.ResourceNotFoundException;

@ControllerAdvice
public class ApiHandlerException {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException exception){

        ErrorMessage errorMessage = new ErrorMessage(
            "NotFound :(",
            HttpStatus.NOT_FOUND.value(),
            exception.getMessage(),
            exception.getClass().getName(),
            new Date().getTime());

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<?> handlerResourceBadRequestException(ResourceBadRequestException exception){
        ErrorMessage errorrMessage = new ErrorMessage(
            "Bad Request",
            HttpStatus.BAD_REQUEST.value(),
            exception.getMessage(),
            exception.getClass().getName(),
            new Date().getTime());

        return new ResponseEntity<>(errorrMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAuthorizationRequestException.class)
    public ResponseEntity<?> handlerResourceAuthorizationRequestException(ResourceAuthorizationRequestException exception){

        ErrorMessage errorrMessage = new ErrorMessage(
            "Unauthorized",
            HttpStatus.UNAUTHORIZED.value(),
            exception.getMessage(),
            exception.getClass().getName(),
            new Date().getTime());

        return new ResponseEntity<>(errorrMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceForBiddenException.class)
    public ResponseEntity<?> handlerResourceForBiddenException(ResourceForBiddenException exception){

        ErrorMessage errorrMessage = new ErrorMessage(
            "ForBidden",
            HttpStatus.FORBIDDEN.value(),
            exception.getMessage(),
            exception.getClass().getName(),
            new Date().getTime());

        return new ResponseEntity<>(errorrMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerInternalServerError(Exception exception){
        ErrorMessage errorMessage = new ErrorMessage(
				"Internal Server Error",
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				exception.getMessage(),
				exception.getClass().getName(),
				new Date().getTime());
		
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
