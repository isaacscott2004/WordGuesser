package model;

public class WrongLetterException extends RuntimeException {
    public WrongLetterException(String message) {
        super(message);
    }
}
