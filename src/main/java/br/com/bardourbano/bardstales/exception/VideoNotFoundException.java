package br.com.bardourbano.bardstales.exception;

public class VideoNotFoundException extends ResourceNotFoundException {

    public VideoNotFoundException() {
        super("VIDEO NÃO ENCONTRADO");
    }

    public VideoNotFoundException(int id) {
        super("VIDEO {id: " + id + "} NÃO ENCONTRADO");
    }

    public VideoNotFoundException(Long id) {
        super("VIDEO {id: " + id + "} NÃO ENCONTRADO");
    }

    public VideoNotFoundException(String id) {
        super("VIDEO {id: " + id + "} NÃO ENCONTRADO");
    }

    public VideoNotFoundException(Throwable cause) {
        super("VIDEO NÃO ENCONTRADO", cause);
    }

    public VideoNotFoundException(int id, Throwable cause) {
        super("VIDEO {id: " + id + "} NÃO ENCONTRADO", cause);
    }

    public VideoNotFoundException(Long id, Throwable cause) {
        super("VIDEO {id: " + id + "} NÃO ENCONTRADO", cause);
    }

    public VideoNotFoundException(String id, Throwable cause) {
        super("VIDEO {id: " + id + "} NÃO ENCONTRADO", cause);
    }
}
