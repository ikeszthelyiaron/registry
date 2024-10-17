package hu.otp.assignment.exception;

import hu.otp.assignment.domain.Platform;

public class AlreadyPresentOnPlatformException extends RuntimeException{
    public AlreadyPresentOnPlatformException(Platform platform) {
        super(String.format("This Person is already present on this Platform: %s", platform));
    }
}
