package hu.otp.assignment.exception;

public class PermAddressInUseException extends RuntimeException{
    public PermAddressInUseException(long id){
        super(String.format("The permanent Address with id %d is in use, therefore it cannot be deleted", id));
    }
}
