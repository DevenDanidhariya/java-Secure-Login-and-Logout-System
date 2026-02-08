package com.java.security.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.java.security.common.dto.AbstractDTO;
import com.java.security.common.enums.Gender;
import com.java.security.common.enums.UserStatus;
import com.java.security.role.dto.RoleDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Deven Danidhariya
 */
@Getter
@Setter
public class UserDTO extends AbstractDTO {

  private String firstName;
  private String lastName;
  private String email;
  private String countryCode;
  private String phoneNumber;
  @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate dob;
  private Gender gender;
  private Set<RoleDTO> roles;
  private UserStatus status;
  private int failedLoginAttempts;
  @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime lastLoginAt;

}
