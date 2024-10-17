package hu.otp.assignment.exception;

import hu.otp.assignment.domain.Platform;

public class ContactDetailAlreadyExistsException extends RuntimeException{
    public ContactDetailAlreadyExistsException(Platform platform, String identfier) {
        super(String.format("The Contact Detail with this Platform and identifier already exists: %s: %s", platform, identfier));
    }
}
