package com.java.security.user.dto;

import com.java.security.common.enums.Gender;
import com.java.security.common.enums.UserStatus;
import com.java.security.role.dto.RoleDTO;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author Deven Danidhariya
 */
public record UserDTO(Long id, String firstName, String lastName, String email,
                      String countryCode, String fullPhoneE164, LocalDate dob, Gender gender,
                      Set<RoleDTO> roles, String password, UserStatus status) {

}
