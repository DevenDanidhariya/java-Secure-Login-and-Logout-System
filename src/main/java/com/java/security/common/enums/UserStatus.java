package com.java.security.common.enums;

import lombok.Getter;

/**
 * @author Deven Danidhariya
 */
@Getter
public enum UserStatus {
  ACTIVE("Active"),
  INACTIVE("Inactive"),
  SUSPENDED("Suspended");

  UserStatus(String value) {
  }
}
