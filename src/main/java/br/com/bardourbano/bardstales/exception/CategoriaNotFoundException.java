package br.com.bardourbano.bardstales.exception;

public class CategoriaNotFoundException extends ResourceNotFoundException {

    public CategoriaNotFoundException() {
        super("CATEGORIA NÃO ENCONTRADA");
    }

    public CategoriaNotFoundException(int id) {
        super("CATEGORIA {id: " + id + "} NÃO ENCONTRADA");
    }

    public CategoriaNotFoundException(Long id) {
        super("CATEGORIA {id: " + id + "} NÃO ENCONTRADA");
    }

    public CategoriaNotFoundException(String id) {
        super("CATEGORIA {id: " + id + "} NÃO ENCONTRADA");
    }

    public CategoriaNotFoundException(Throwable cause) {
        super("CATEGORIA NÃO ENCONTRADA", cause);
    }

    public CategoriaNotFoundException(int id, Throwable cause) {
        super("CATEGORIA {id: " + id + "} NÃO ENCONTRADA", cause);
    }

    public CategoriaNotFoundException(Long id, Throwable cause) {
        super("CATEGORIA {id: " + id + "} NÃO ENCONTRADA", cause);
    }

    public CategoriaNotFoundException(String id, Throwable cause) {
        super("CATEGORIA {id: " + id + "} NÃO ENCONTRADA", cause);
    }
}
