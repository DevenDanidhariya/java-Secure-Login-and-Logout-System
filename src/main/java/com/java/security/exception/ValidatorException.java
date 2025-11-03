package com.java.security.exception;

import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Deven Danidhariya
 */
@Getter
public class ValidatorException extends ResponseStatusException {

  private transient ApiError apiError = null;
  private transient List<ApiError> apiErrors = null;

  public ValidatorException(ApiError apiError) {
    super(HttpStatus.BAD_REQUEST, String.format("%s error message", apiError));
    this.apiError = apiError;
  }

  public ValidatorException(List<ApiError> apiErrors) {
    super(HttpStatus.BAD_REQUEST, String.format("%s error messages", apiErrors));
    this.apiErrors = apiErrors;
  }

  public static void getListOfApiError(List<ApiError> apiErrors, ApiError error) {
    apiErrors.add(error);
  }

  public static void apiErrors(List<ApiError> apiErrors) {
    throw new ValidatorException(apiErrors);
  }

  public static ApiError getApiError(int errorCode, String message) {
    return new ApiError(errorCode, message);
  }

  public static void apiError(int errorCode, String message) {
    throw new ValidatorException(new ApiError(errorCode, message));
  }
}
