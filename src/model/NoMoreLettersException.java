package model;

public class NoMoreLettersException extends RuntimeException {
    public NoMoreLettersException(String message) {
        super(message);
    }
}
