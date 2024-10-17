package hu.otp.assignment.exception;

public class AlreadyHasTempAddressException extends RuntimeException{
    public AlreadyHasTempAddressException(long id) {
        super(String.format("The person with id %d already has a temporary Address", id));
    }
}
