package hu.otp.assignment.exception;

public class AddressClashException extends RuntimeException{
    public AddressClashException() {
        super("Permanent and temporary Addresses are not supposed to be the same");
    }
}
