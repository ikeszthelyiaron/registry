package hu.otp.assignment.exception;

public class AddressIsPermanentException extends RuntimeException{
    public AddressIsPermanentException(long id) {
        super(String.format("Address with id %d is permanent, not temporary", id));
    }
}
