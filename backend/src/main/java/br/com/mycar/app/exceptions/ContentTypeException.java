package br.com.mycar.app.exceptions;

public class ContentTypeException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public ContentTypeException(String message) {
        super(message);
    }
}
