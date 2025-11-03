package com.java.security.role.service;

import static com.java.security.exception.ValidatorException.apiError;
import static com.java.security.exception.ValidatorException.getApiError;

import com.java.security.exception.ApiError;
import com.java.security.exception.ValidatorException;
import com.java.security.role.dto.RoleDTO;
import com.java.security.role.mapper.RoleMapper;
import com.java.security.role.model.Role;
import com.java.security.role.repository.RoleRepository;
import io.micrometer.common.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Deven Danidhariya
 */
@Service
public class RoleService {

  private final RoleRepository roleRepository;

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public RoleDTO createRole(RoleDTO roleDTO) {
    List<ApiError> apiErrorList = new ArrayList<>();
    try {
      if (roleDTO == null || roleDTO.name() == null) {
        ValidatorException.getListOfApiError(apiErrorList,
            ValidatorException.getApiError(HttpStatus.BAD_REQUEST.value(),
                "Error : Invalid request payload"));
      }

      if (roleDTO.name().trim().length() > 20) {
        ValidatorException.getListOfApiError(apiErrorList,
            getApiError(HttpStatus.BAD_REQUEST.value(),
                "Error :Role name must be up to '20' characters long"));
      }
      if (roleDTO.description().trim().length() > 255) {
        ValidatorException.getListOfApiError(apiErrorList,
            getApiError(HttpStatus.BAD_REQUEST.value(),
                "Error :Role description must be up to '255' characters long"));
      }
      if (roleRepository.findByNameAndIsDeleted(roleDTO.name(), false)
          .isPresent()) {
        ValidatorException.getListOfApiError(apiErrorList,
            getApiError(HttpStatus.BAD_REQUEST.value(),
                "Error : role already present"));
      }
      Role role = RoleMapper.toEntity(roleDTO);
      if (apiErrorList.isEmpty()) {
        return RoleMapper.toDTO(roleRepository.save(role));
      }
      return null;
    } finally {
      if (!apiErrorList.isEmpty()) {
        ValidatorException.apiErrors(apiErrorList);
      }
    }

  }

  public RoleDTO updateRole(RoleDTO roleDTO) {
    List<ApiError> apiErrorList = new ArrayList<>();
    try {
      if (roleDTO == null) {
        throw new ValidatorException(ValidatorException.getApiError(HttpStatus.BAD_REQUEST.value(),
            "Error : Invalid request payload"));
      }

      if (roleDTO.id() == null || roleDTO.id() <= 0) {
        ValidatorException.getListOfApiError(apiErrorList,
            ValidatorException.getApiError(HttpStatus.BAD_REQUEST.value(),
                "Error : Invalid role id provided"));
      }
      if (StringUtils.isBlank(roleDTO.name())) {
        ValidatorException.getListOfApiError(apiErrorList,
            ValidatorException.getApiError(HttpStatus.BAD_REQUEST.value(),
                "Error : Missing role name"));
      }
      if (roleDTO.name().trim().length() > 20) {
        ValidatorException.getListOfApiError(apiErrorList,
            getApiError(HttpStatus.BAD_REQUEST.value(),
                "Error :Role name must be up to '20' characters long"));
      }
      if (roleDTO.description().trim().length() > 255) {
        ValidatorException.getListOfApiError(apiErrorList,
            getApiError(HttpStatus.BAD_REQUEST.value(),
                "Error :Role description must be up to '255' characters long"));
      }

      Optional<Role> roleOptional = roleRepository.findByNameAndIsDeleted(roleDTO.name().trim(),
          false);

      Role role = roleRepository.findByIdAndIsDeleted(roleDTO.id(), false)
          .orElseThrow(() -> new ValidatorException(
              ValidatorException.getApiError(HttpStatus.BAD_REQUEST.value(),
                  "Error : role not found")));

      if (!Objects.equals(role.getId(), roleOptional.get().getId())) {
        ValidatorException.getListOfApiError(apiErrorList,
            ValidatorException.getApiError(HttpStatus.BAD_REQUEST.value(),
                "Error : Duplicate Entry are not allowed"));
      }

      role.setName(roleDTO.name().trim().toUpperCase());
      role.setDescription(roleDTO.description().trim());
      if (apiErrorList.isEmpty()) {
        return RoleMapper.toDTO(roleRepository.save(role));
      }
      return null;
    } finally {
      if (!apiErrorList.isEmpty()) {
        ValidatorException.apiErrors(apiErrorList);
      }
    }
  }

  public void deleteRole(Long id) {
    if (id == null || id <= 0) {
      apiError(HttpStatus.BAD_REQUEST.value(),
          "Error : Invalid role id provided");
    }
    Role role = roleRepository.findByIdAndIsDeleted(id, false)
        .orElseThrow(() -> new ValidatorException(
            ValidatorException.getApiError(HttpStatus.BAD_REQUEST.value(),
                "Error : role not found")));

    role.setDeleted(true);
    roleRepository.save(role);
  }

  public RoleDTO getRoleById(Long id) {
    if (id == null || id <= 0) {
      apiError(HttpStatus.BAD_REQUEST.value(),
          "Error : Invalid role id provided");
    }
    Role role = roleRepository.findByIdAndIsDeleted(id, false)
        .orElseThrow(() -> new ValidatorException(
            ValidatorException.getApiError(HttpStatus.BAD_REQUEST.value(),
                "Error : role not found")));

    return RoleMapper.toDTO(role);
  }

  public List<RoleDTO> getAllRole() {
    List<Role> roleList = roleRepository.findAllByIsDeleted(false);
    if (roleList.isEmpty()) {
      return Collections.emptyList();
    }
    return roleList.stream().map(RoleMapper::toDTO).toList();
  }
}
