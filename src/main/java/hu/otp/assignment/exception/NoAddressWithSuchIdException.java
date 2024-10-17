package hu.otp.assignment.exception;

public class NoAddressWithSuchIdException extends RuntimeException{
    public NoAddressWithSuchIdException(long id) {
        super(String.format("There is no Address with such id %d", id));
    }
}
