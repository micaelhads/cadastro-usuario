package com.cadastro.usuario.exeptions;

public class InvalidParameterException extends RuntimeException{
    public InvalidParameterException(String menssage){
        super(menssage);
    }
}
