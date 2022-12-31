package br.com.mycar.app.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleAllUncaughtException(Exception e) {
        log.error("An unknown error has occurred.", e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage());
        problemDetail.getStatus();
        problemDetail.setType(URI.create("http://localhost:8080"));
        problemDetail.setTitle("Internal server error");
        problemDetail.setDetail("An unknown error has occurred.");
        return problemDetail;
    }

    @ExceptionHandler(ContentTypeException.class)
    public ProblemDetail handleContentTypeException(ContentTypeException e) {
        log.error("Invalid file extension", e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                e.getMessage());
        problemDetail.getStatus();
        //problemDetail.setType(URI.create("http://localhost:8080"));
        problemDetail.setTitle("Bad request");
        problemDetail.setDetail("extension not allowed\n. Send files with extensions: JPG, PNG or JPEG.");
        return problemDetail;
    }

    @ExceptionHandler(EmptyFileException.class)
    public ProblemDetail handleEmptyFileException(EmptyFileException e) {
        log.error("You have not uploaded any files.", e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                e.getMessage());
        problemDetail.getStatus();
        problemDetail.setType(URI.create("http://localhost:8080"));
        problemDetail.setTitle("Bad request");
        problemDetail.setDetail("It is necessary to send some file. Send files in JPG, PNG or JPEG formats.");
        return problemDetail;
    }
}
