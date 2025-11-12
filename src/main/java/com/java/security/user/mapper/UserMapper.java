package com.java.security.user.mapper;

import com.java.security.role.mapper.RoleMapper;
import com.java.security.user.dto.UserDTO;
import com.java.security.user.model.User;
import com.java.security.utils.PhoneNumUtil;
import java.util.stream.Collectors;

/**
 * @author Deven Danidhariya
 */
public class UserMapper {

  public static User toEntity(UserDTO userDTO) {
    User user = new User();

    user.setFirstName(userDTO.firstName());
    user.setLastName(userDTO.lastName());
    user.setEmail(userDTO.email());
    user.setCountryCode(userDTO.countryCode());
    user.setFullPhoneE164(
        PhoneNumUtil.formatToE164(userDTO.countryCode(), userDTO.fullPhoneE164()));
    user.setDob(userDTO.dob());
    user.setPassword(userDTO.password());

    return user;
  }

  public static UserDTO toDTO(User user) {
    return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
        user.getCountryCode(), user.getFullPhoneE164(), user.getDob(), user.getGender(),
        user.getRoles().stream().map(RoleMapper::toDTO).collect(Collectors.toSet()), null,
        user.getStatus());
  }

}
