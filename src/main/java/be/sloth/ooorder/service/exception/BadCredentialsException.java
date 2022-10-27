package be.sloth.ooorder.service.exception;

public class BadCredentialsException extends RuntimeException{

    public BadCredentialsException() {
        super("invalid credentials!");
    }
}
