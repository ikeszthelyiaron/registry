package hu.otp.assignment.exception.handler;

import hu.otp.assignment.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String VALIDATION_ERROR_MESSAGE = "Error during validation on field '{}' with message '{}'";

    @ExceptionHandler({AddressAlreadyInUseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleAddressAlreadyInUseException(AddressAlreadyInUseException exception) {
        ValidationError validationError = new ValidationError("id", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }

    @ExceptionHandler({AddressClashException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleAddressClashException(AddressClashException exception) {
        ValidationError validationError = new ValidationError("Address", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }

    @ExceptionHandler({AddressIsPermanentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleAddressIsPermanentException(AddressIsPermanentException exception) {
        ValidationError validationError = new ValidationError("id", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }

    @ExceptionHandler({AddressIsTemporaryException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleAddressIsTemporaryException(AddressIsTemporaryException exception) {
        ValidationError validationError = new ValidationError("id", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }

    @ExceptionHandler({AlreadyHasTempAddressException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleAlreadyHasTempAddressException(AlreadyHasTempAddressException exception) {
        ValidationError validationError = new ValidationError("id", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }

    @ExceptionHandler({ContactDetailAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleContactDetailAlreadyExistsException(ContactDetailAlreadyExistsException exception) {
        ValidationError validationError = new ValidationError("ContactDetail", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }

    @ExceptionHandler({NoAddressWithSuchIdException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleNoAddressWithSuchIdException(NoAddressWithSuchIdException exception) {
        ValidationError validationError = new ValidationError("id", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }

    @ExceptionHandler({NoContactDetailWithSuchIdException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleNoContactDetailWithSuchIdException(NoContactDetailWithSuchIdException exception) {
        ValidationError validationError = new ValidationError("id", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }

    @ExceptionHandler({NoPersonWithSuchIdException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleNoPersonWithSuchIdException(NoPersonWithSuchIdException exception) {
        ValidationError validationError = new ValidationError("id", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }

    @ExceptionHandler({NoSuchContactDetailException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleNoSuchContactDetailException(NoSuchContactDetailException exception) {
        ValidationError validationError = new ValidationError("ContactDetail", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }

    @ExceptionHandler({NoTempAddressException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleNoTempAddressException(NoTempAddressException exception) {
        ValidationError validationError = new ValidationError("id", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }

    @ExceptionHandler({PermAddressInUseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handlePermAddressInUseException(PermAddressInUseException exception) {
        ValidationError validationError = new ValidationError("id", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }

    @ExceptionHandler({AlreadyPresentOnPlatformException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleAlreadyPresentOnPlatformException(AlreadyPresentOnPlatformException exception) {
        ValidationError validationError = new ValidationError("Platform", exception.getMessage());
        log.error(VALIDATION_ERROR_MESSAGE, validationError.field(), validationError.errorMessage());
        return validationError;
    }


}
