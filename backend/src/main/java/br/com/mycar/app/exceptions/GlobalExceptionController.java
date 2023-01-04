package br.com.mycar.app.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Date;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageExceptionHandler> handleAllUncaughtException(Exception e, WebRequest request) {
        log.error("An unknown error has occurred.", e);
        String type = URI.create("http://localhost:8080").toString();
        String title = "Internal Server Error";
        String detail = e.getMessage();
        if (detail == null) detail = e.toString();
        String instance = request.getDescription(false).substring(4);

        MessageExceptionHandler error = new MessageExceptionHandler(type, title, HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred: " + detail, instance, new Date());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageExceptionHandler> handleAllUncaughtException(BadCredentialsException e,
                                                                              WebRequest request) {
        log.error("Bad credential was detected.", e);
        String type = URI.create("http://localhost:8080").toString();
        String title = "Bad credentials";
        String detail = e.getMessage();
        if (detail == null) detail = e.toString();
        String instance = request.getDescription(false).substring(4);

        MessageExceptionHandler error = new MessageExceptionHandler(type, title, HttpStatus.UNAUTHORIZED.value(),
                "Invalid credentials: " + detail, instance, new Date());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<MessageExceptionHandler> handleUnauthorizedException(HttpClientErrorException.Forbidden e,
                                                                               WebRequest request) {
        log.error("Forbidden.", e);
        String type = URI.create("http://localhost:8080").toString();
        String title = "Forbidden!";
        String detail = e.getMessage();
        if (detail == null) detail = e.toString();
        String instance = request.getDescription(false).substring(4);

        MessageExceptionHandler error = new MessageExceptionHandler(type, title, HttpStatus.FORBIDDEN.value(),
                "Unable to complete authentication: " + detail, instance, new Date());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageExceptionHandler> handleUnauthorizedException(AccessDeniedException e,
                                                                               WebRequest request) {
        log.error("Unauthorized access.", e);
        String type = URI.create("http://localhost:8080").toString();
        String title = "Unauthorized access";
        String detail = e.getMessage();
        if (detail == null) detail = e.toString();
        String instance = request.getDescription(false).substring(4);

        MessageExceptionHandler error = new MessageExceptionHandler(type, title, HttpStatus.UNAUTHORIZED.value(),
                "Ops: Unauthorized: " + detail, instance, new Date());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ContentTypeException.class)
    public ResponseEntity<MessageExceptionHandler> handleContentTypeException(ContentTypeException e,
                                                                              WebRequest request) {
        log.error("Invalid file extension", e);
        String type = URI.create("http://localhost:8080").toString();
        String title = "Bad request";
        String detail = e.getMessage();
        if (detail == null) detail = e.toString();
        String instance = request.getDescription(false).substring(4);

        MessageExceptionHandler error = new MessageExceptionHandler(type, title, HttpStatus.BAD_REQUEST.value(),
                "Extension not allowed. " + detail, instance, new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyFileException.class)
    public ResponseEntity<MessageExceptionHandler> handleEmptyFileException(EmptyFileException e,
                                                                       WebRequest request) {
        log.error("You have not uploaded any files.", e);
        String type = URI.create("http://localhost:8080").toString();
        String title = "Bad request";
        String detail = e.getMessage();
        if (detail == null) detail = e.toString();
        String instance = request.getDescription(false).substring(4);

        MessageExceptionHandler error = new MessageExceptionHandler(type, title, HttpStatus.BAD_REQUEST.value(),
                "Extension not allowed. " + detail, instance, new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
