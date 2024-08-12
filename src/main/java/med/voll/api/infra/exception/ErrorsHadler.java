package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsHadler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404Handle() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400Handle(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorValidation::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity error400Handle(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity errorBadCredentialsHandle() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials!");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity errorAuthenticationHandle() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed!");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity errorAccessDeniedHandle() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied!");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity error500Handle(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " +ex.getLocalizedMessage());
    }

    private record DataErrorValidation(String field, String message) {
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
