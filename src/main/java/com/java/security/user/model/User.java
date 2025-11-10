package com.java.security.user.model;

import com.java.security.common.enums.Gender;
import com.java.security.common.enums.UserStatus;
import com.java.security.common.model.AbstractModel;
import com.java.security.role.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Deven Danidhariuya
 */
@Getter
@Setter
@Entity
@Table(name = "user", indexes = {
    @Index(name = "idx_email_phone", columnList = "email, fullPhoneE164")})
public class User extends AbstractModel {

  @Column(name = "first_name", nullable = false, length = 50)
  private String firstName;

  @Column(name = "last_name", nullable = true, length = 70)
  private String lastName;

  @Column(name = "email", nullable = false, length = 70)
  private String email;

  @Column(name = "country_code", nullable = false, length = 5)
  private String countryCode;

  @Column(name = "full_phone_e164", nullable = false, length = 18)
  private String fullPhoneE164;

  @Column(name = "dob", nullable = false)
  private LocalDate dob;

  @Column(name = "gender", nullable = false, length = 10)
  private Gender gender;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  @Column(name = "password", nullable = false, length = 30)
  private String password;

  @Column(name = "status", nullable = false, length = 30)
  private UserStatus status;

  @Column(name = "failed_login_attempts")
  private int failedLoginAttempts;

  @Column(name = "last_login_at", nullable = true)
  private LocalDateTime lastLoginAt;

  @Column(name = "password_updated_at", nullable = true)
  private LocalDateTime passwordUpdatedAt;

  @Column(name = "reset_token", nullable = true)
  private String resetToken;

  @Column(name = "reset_token_expiry", nullable = true)
  private LocalDateTime resetTokenExpiry;

}
