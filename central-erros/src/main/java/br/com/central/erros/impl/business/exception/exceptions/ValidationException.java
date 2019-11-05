package br.com.central.erros.impl.business.exception.exceptions;

public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ValidationException(String s) {
        super(s);
    }
}
