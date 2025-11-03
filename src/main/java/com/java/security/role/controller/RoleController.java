package com.java.security.role.controller;

import com.java.security.common.dto.ResponseDTO;
import com.java.security.role.dto.RoleDTO;
import com.java.security.role.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Deven Danidhariya
 */
@Tag(name = "Role")
@RestController
@RequestMapping("/role")
public class RoleController {

  private final RoleService roleService;

  @Autowired
  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @PostMapping
  public ResponseEntity<ResponseDTO> createRole(@RequestBody RoleDTO roleDTO) {

    RoleDTO role = roleService.createRole(roleDTO);

    return new ResponseEntity<>(
        new ResponseDTO(HttpStatus.OK.value(), "Role Created Successfully", role),
        new HttpHeaders(), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<ResponseDTO> updateRole(@RequestBody RoleDTO roleDTO) {

    RoleDTO role = roleService.updateRole(roleDTO);

    return new ResponseEntity<>(
        new ResponseDTO(HttpStatus.OK.value(), "Role updated Successfully", role),
        new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDTO> deleteRole(@PathVariable Long id) {
    roleService.deleteRole(id);
    return new ResponseEntity<>(
        new ResponseDTO(HttpStatus.OK.value(), "Role Deleted Successfully", null),
        new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDTO> retrieveRole(@PathVariable Long id) {

    RoleDTO role = roleService.getRoleById(id);

    return new ResponseEntity<>(
        new ResponseDTO(HttpStatus.OK.value(), "Role Retrieved Successfully", role),
        new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<ResponseDTO> retrieveAllRole() {

    List<RoleDTO> allRole = roleService.getAllRole();

    return new ResponseEntity<>(
        new ResponseDTO(HttpStatus.OK.value(), "All Role Retrieved Successfully", allRole),
        new HttpHeaders(), HttpStatus.OK);
  }
}
