package hu.otp.assignment.exception;

public class NoPersonWithSuchIdException extends RuntimeException{
    public NoPersonWithSuchIdException(long personId) {
        super(String.format("There is no person with this id: %d", personId));
    }
}
