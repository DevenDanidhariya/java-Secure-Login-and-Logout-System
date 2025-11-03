package com.java.security.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Deven Danidhariya
 */
@Getter
@Setter
public class ApiError {

  private int errorCode;
  private String description;

  public ApiError() {
  }

  public ApiError(int errorCode, String description) {
    this.errorCode = errorCode;
    this.description = description;
  }
}
