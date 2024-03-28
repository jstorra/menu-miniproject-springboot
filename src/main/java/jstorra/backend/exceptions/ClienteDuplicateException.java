package jstorra.backend.exceptions;

public class ClienteDuplicateException extends RuntimeException {
    public ClienteDuplicateException(String message) {
        super(message);
    }
}
