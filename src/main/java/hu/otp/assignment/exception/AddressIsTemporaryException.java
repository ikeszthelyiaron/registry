package hu.otp.assignment.exception;

public class AddressIsTemporaryException extends RuntimeException{
    public AddressIsTemporaryException(long id) {
        super(String.format("Address with id %d is temporary, not permanent", id));
    }
}
