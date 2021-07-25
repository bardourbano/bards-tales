package br.com.bardourbano.bardstales.error;

abstract class ApiSubError {

}

class ApiValidationError extends ApiSubError {
    private String objeto;
    private String campo;
    private String valor;
    private String mensagem;

    public ApiValidationError(String objeto, String mensagem) {
        this.objeto = objeto;
        this.mensagem = mensagem;
    }
}