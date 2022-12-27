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
        log.error("Ocorreu um erro desconhecido", e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage());
        problemDetail.getStatus();
        problemDetail.setType(URI.create("http://localhost:8080"));
        problemDetail.setTitle("Bad request");
        problemDetail.setDetail("Ops! Ocorreu um erro desconhecido");
        return problemDetail;
    }

    @ExceptionHandler(ContentTypeException.class)
    public ProblemDetail handleContentTypeException(ContentTypeException e) {
        log.error("Extensão de arquivo inválida", e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                e.getMessage());
        problemDetail.getStatus();
        //problemDetail.setType(URI.create("http://localhost:8080"));
        problemDetail.setTitle("Bad request");
        problemDetail.setDetail("Ops! Extensão não permitida. Envie arquivos nos formatos JPG, PNG ou JPEG.");
        return problemDetail;
    }

    @ExceptionHandler(EmptyFileException.class)
    public ProblemDetail handleEmptyFileException(EmptyFileException e) {
        log.error("Você não enviou nenhum arquivo.", e);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                e.getMessage());
        problemDetail.getStatus();
        problemDetail.setType(URI.create("http://localhost:8080"));
        problemDetail.setTitle("Bad request");
        problemDetail.setDetail("É necessário enviar algum arquivo. Envie arquivos nos formatos JPG, PNG ou JPEG.");
        return problemDetail;
    }
}
