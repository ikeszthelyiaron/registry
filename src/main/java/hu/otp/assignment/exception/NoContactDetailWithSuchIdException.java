package hu.otp.assignment.exception;

public class NoContactDetailWithSuchIdException extends RuntimeException{
    public NoContactDetailWithSuchIdException(long id) {
        super(String.format("There is no ContactDetail with id %d", id));
    }
}
