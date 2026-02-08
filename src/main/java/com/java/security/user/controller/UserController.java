package com.java.security.user.controller;

import com.java.security.common.dto.ResponseDTO;
import com.java.security.user.dto.UserDTO;
import com.java.security.user.dto.UserFilterDTO;
import com.java.security.user.dto.UserRequestDTO;
import com.java.security.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
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

@Tag(name = "User")
@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<ResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {

    UserDTO user = userService.registerUser(userRequestDTO);

    return new ResponseEntity<>(
        new ResponseDTO(HttpStatus.OK.value(), "User Created Successfully", user),
        new HttpHeaders(), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO userDTO) {

    UserDTO user = userService.updateUserInformation(userDTO);

    return new ResponseEntity<>(
        new ResponseDTO(HttpStatus.OK.value(), "User updated Successfully", user),
        new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDTO> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return new ResponseEntity<>(
        new ResponseDTO(HttpStatus.OK.value(), "User Deleted Successfully", null),
        new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDTO> getUserById(@PathVariable Long id) {

    UserDTO user = userService.fetchUserById(id);

    return new ResponseEntity<>(
        new ResponseDTO(HttpStatus.OK.value(), "User Retrieved Successfully", user),
        new HttpHeaders(), HttpStatus.OK);
  }

  @PostMapping("/all")
  public ResponseEntity<ResponseDTO> getAllUsersWithFilter(@RequestBody UserFilterDTO filterDTO) {

    Page<UserDTO> users = userService.retrieveUsers();

    return new ResponseEntity<>(
        new ResponseDTO(HttpStatus.OK.value(), "All User Retrieved Successfully", users),
        new HttpHeaders(), HttpStatus.OK);
  }
}
