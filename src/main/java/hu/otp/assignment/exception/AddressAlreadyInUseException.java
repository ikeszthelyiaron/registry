package hu.otp.assignment.exception;

public class AddressAlreadyInUseException extends RuntimeException{
    public AddressAlreadyInUseException(long id){
        super(String.format("Address with id %d is already in use", id));
    }
}
