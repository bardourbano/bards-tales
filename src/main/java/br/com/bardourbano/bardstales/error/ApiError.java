package br.com.bardourbano.bardstales.error;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;

public class ApiError {

    public HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public LocalDateTime timestamp;
    public String mensagem;
    public String mensagemDegug;
    public List<ApiSubError> subErrors;

    public ApiError(HttpStatus httpStatus, Throwable ex) {
        this.timestamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.mensagemDegug = ex.getLocalizedMessage();
    }

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
