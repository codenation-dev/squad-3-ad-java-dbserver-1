package br.com.central.erros.impl.business.exception.exceptions;

public class DataIntegrityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataIntegrityException(String s) {

        super(s);
    }

    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
