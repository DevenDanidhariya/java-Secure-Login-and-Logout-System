package com.java.security.role.mapper;

import com.java.security.role.dto.RoleDTO;
import com.java.security.role.model.Role;
import org.springframework.stereotype.Component;

/**
 * @author Deven Danidhariya
 */
@Component
public class RoleMapper {

  private RoleMapper() {
  }

  public static Role toEntity(RoleDTO roleDTO) {
    Role role = new Role();
    role.setName(roleDTO.name().trim().toUpperCase());
    role.setDescription(roleDTO.description().trim());
    return role;
  }

  public static RoleDTO toDTO(Role role) {
    return new RoleDTO(role.getId(), role.getName(), role.getDescription());
  }
}
