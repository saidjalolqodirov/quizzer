package uz.test.exception;

public class NotFountException extends RuntimeException {
    private String message;

    public NotFountException() {
        super();
    }

    public NotFountException(String message) {
        super(message);
    }
}
