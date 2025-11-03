package com.java.security.role.model;

import com.java.security.common.model.AbstractModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Deven Danidhariya
 */
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends AbstractModel {

  @Column(name = "name", unique = true, nullable = false, length = 20)
  private String name;

  @Column(name = "description", length = 255)
  private String description;

}
