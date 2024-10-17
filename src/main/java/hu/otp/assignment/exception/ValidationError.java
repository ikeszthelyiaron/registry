package hu.otp.assignment.exception;

public record ValidationError(String field, String errorMessage) {
}
