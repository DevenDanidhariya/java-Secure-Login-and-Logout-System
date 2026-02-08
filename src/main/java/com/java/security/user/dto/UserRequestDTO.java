package com.java.security.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.java.security.common.enums.Gender;
import com.java.security.common.enums.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

  @NotBlank(message = "First Name can't be blank.")
  private String firstName;
  @NotBlank(message = "Last Name can't be blank.")
  private String lastName;
  @NotBlank(message = "Email can't be blank.")
  private String email;
  @NotBlank(message = "County Code can't be blank.")
  private String countryCode;
  @NotBlank(message = "Phone Number can't be blank.")
  private String phoneNumber;
  @NotNull(message = "Date of birth can't be NULL.")
  @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate dob;
  @NotNull(message = "Gender can't be NULL.")
  private Gender gender;
  @NotBlank(message = "Password can't be Blank.")
  private String password;
  @NotEmpty(message = "Role can' be empty.")
  private Set<Long> roleIds;
  @NotNull(message = "Status can't be NULL.")
  private UserStatus status;

}
