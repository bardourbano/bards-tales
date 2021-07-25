package br.com.bardourbano.bardstales.error;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;

public class ApiError {

    public final HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public final LocalDateTime timestamp;
    public final String mensagem;
    public final String mensagemDegug;

    public ApiError(HttpStatus httpStatus, String mensagem, Throwable ex) {
        this.timestamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.mensagem = mensagem;
        this.mensagemDegug = ex.getLocalizedMessage();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
