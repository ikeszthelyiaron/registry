package hu.otp.assignment.exception;

public class NoTempAddressException extends RuntimeException{
    public NoTempAddressException(long id) {
        super(String.format("The person with id %d has no temporary Address", id));
    }
}
