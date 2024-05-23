package com.parcialTienda.Aplicativo.web.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String message) {
        super(message);
    }
}
