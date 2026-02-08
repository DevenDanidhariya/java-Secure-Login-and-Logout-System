package com.java.security.user.mapper;

import static com.java.security.utils.PhoneNumUtil.formatToE164;

import com.java.security.role.mapper.RoleMapper;
import com.java.security.user.dto.UserDTO;
import com.java.security.user.dto.UserRequestDTO;
import com.java.security.user.model.User;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  private UserMapper() {
  }

  public static UserDTO toDTO(User user) {
    UserDTO userDTO = new UserDTO();

    userDTO.setId(user.getId());
    userDTO.setFirstName(user.getFirstName());
    userDTO.setLastName(user.getLastName());
    userDTO.setEmail(user.getEmail());
    userDTO.setCountryCode(user.getCountryCode());
    userDTO.setPhoneNumber(user.getPhoneNumber());
    userDTO.setDob(user.getDob());
    userDTO.setGender(user.getGender());
    userDTO.setRoles(user.getRoles().stream().map(RoleMapper::toDTO).collect(Collectors.toSet()));
    userDTO.setStatus(user.getStatus());
    userDTO.setFailedLoginAttempts(user.getFailedLoginAttempts());
    userDTO.setLastLoginAt(user.getLastLoginAt());

    return userDTO;
  }

  public static User toEntity(UserDTO userDTO) {
    User user = new User();

    user.setFirstName(userDTO.getFirstName().trim());
    user.setLastName(userDTO.getLastName().trim());
    user.setEmail(userDTO.getEmail().trim());
    user.setCountryCode(userDTO.getCountryCode().trim());
    user.setPhoneNumber(userDTO.getPhoneNumber().trim());
    user.setFullPhoneE164(userDTO.getPhoneNumber().trim());
    user.setDob(userDTO.getDob());
    user.setGender(userDTO.getGender());
//    user.setRoles();
//    user.setPassword();
    user.setStatus(userDTO.getStatus());
    user.setFailedLoginAttempts(userDTO.getFailedLoginAttempts());
    user.setLastLoginAt(userDTO.getLastLoginAt());

    return user;
  }

  public static User toEntity(UserRequestDTO dto) {
    User user = new User();

    user.setFirstName(dto.getFirstName().trim());
    user.setLastName(dto.getLastName().trim());
    user.setEmail(dto.getEmail().trim());
    user.setCountryCode(dto.getCountryCode().trim());
    user.setPhoneNumber(dto.getPhoneNumber().trim());
    user.setFullPhoneE164(formatToE164(dto.getCountryCode().trim(), dto.getPhoneNumber().trim()));
    user.setDob(dto.getDob());
    user.setGender(dto.getGender());
    user.setPassword(dto.getPassword());
    user.setStatus(dto.getStatus());

    return user;
  }

}
