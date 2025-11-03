package com.java.security.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Deven Danidhariya
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger LOG = LogManager.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler({ValidatorException.class})
  public ResponseEntity<Object> handleValidatorException(ValidatorException validatorException) {
    if (validatorException == null) {
      LOG.error("ValidatorException object is null");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of(
          new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
              "Unexpected null validator exception")));
    }

    List<ApiError> errors = new ArrayList<>();

    if (validatorException.getApiErrors() != null && !validatorException.getApiErrors().isEmpty()) {
      errors.addAll(validatorException.getApiErrors());
    }

    if (validatorException.getApiError() != null) {
      errors.add(validatorException.getApiError());
    }

    if (errors.isEmpty()) {
      LOG.error("ValidatorException did not contain any ApiError or ApiErrors.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
          List.of(new ApiError(HttpStatus.BAD_REQUEST.value(), "No validation details available")));
    }

    String errorSummary = errors.stream()
        .map(e -> String.format("[code=%s, msg=%s]", e.getErrorCode(), e.getDescription()))
        .collect(Collectors.joining(", "));

    LOG.error("Validation failed with {} error(s): {}", errors.size(), errorSummary);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
    LOG.error("Runtime exception occurred: {}", exception.getMessage(), exception);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationExceptions(
      MethodArgumentNotValidException exception) {
    Map<String, String> errors = new HashMap<>();
    exception.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    LOG.error(errors);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGenericException(Exception exception) {
    LOG.error("error : An unexpected error occurred");
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
