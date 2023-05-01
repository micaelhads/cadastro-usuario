package com.cadastro.usuario.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {
    @ExceptionHandler(UsuarioNotFoundException.class)
    public final ResponseEntity<StandardException> usuarioNotFound(InvalidParameterException e) {
        StandardException standardExecption = new StandardException(e.getMessage(), System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(standardExecption, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public final ResponseEntity<StandardException> invalidParameter(InvalidParameterException e) {
        StandardException standardExecption = new StandardException(e.getMessage(), System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(standardExecption, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
