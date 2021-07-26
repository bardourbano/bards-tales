package br.com.bardourbano.bardstales.error;

import lombok.AllArgsConstructor;

abstract class ApiSubError {

}

@AllArgsConstructor
class ApiValidationError extends ApiSubError {
    public String campo;
    public String mensagem;
    public Object valorRecebido;
}