package com.cadastro.usuario.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(String menssage){
        super(menssage);
    }
    public UsuarioNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
