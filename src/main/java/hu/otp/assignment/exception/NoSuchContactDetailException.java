package hu.otp.assignment.exception;

import hu.otp.assignment.domain.Platform;

public class NoSuchContactDetailException extends RuntimeException{
    public NoSuchContactDetailException(long personId, Platform platform) {
        super(String.format("There is no %s Contact Detail for the Person with id %d", platform, personId));
    }
}
