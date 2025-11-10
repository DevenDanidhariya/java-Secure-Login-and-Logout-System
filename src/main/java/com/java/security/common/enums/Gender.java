package com.java.security.common.enums;

import lombok.Getter;

/**
 * @author Deven Danidhariya
 */
@Getter
public enum Gender {
  MALE("Male"),
  FEMALE("Female"),
  OTHER("Other");

  Gender(String value) {
  }
}
