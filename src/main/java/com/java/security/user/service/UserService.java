package com.java.security.user.service;

import com.java.security.role.model.Role;
import com.java.security.role.repository.RoleRepository;
import com.java.security.user.dto.UserDTO;
import com.java.security.user.dto.UserRequestDTO;
import com.java.security.user.mapper.UserMapper;
import com.java.security.user.model.User;
import com.java.security.user.repository.UserRepository;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static final Logger LOG = LogManager.getLogger(UserService.class);
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;

  public UserService(RoleRepository roleRepository, UserRepository userRepository) {
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
  }


  public UserDTO registerUser(UserRequestDTO dto) {
    User entity = UserMapper.toEntity(dto);
    Set<Role> roles = roleRepository.findRolesByIdsAndIsDeleted(dto.getRoleIds(),
        false);
    LOG.info("roles are fetched.");
    if (!roles.isEmpty()) {
      entity.setRoles(roles);
    }
    User saved = userRepository.save(entity);
    LOG.info("user details saved.");
    return UserMapper.toDTO(saved);
  }

  public UserDTO updateUserInformation(UserDTO userDTO) {

    return null;
  }

  public void deleteUser(Long id) {

  }

  public UserDTO fetchUserById(Long id) {

    return null;
  }

  public Page<UserDTO> retrieveUsers() {
    return null;
  }

}
