package com.java.security.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Deven Danidhariya
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractModel {

  public AbstractModel() {
  }

  public AbstractModel(Long id) {
    this.id = id;
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long id;

  @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT false")
  private boolean isDeleted = false;

  @CreatedBy
  @Column(name = "created_by")
  private String createdBy;

  @CreatedDate
  @Column(name = "created_on")
  private LocalDateTime createdOn;

  @LastModifiedBy
  @Column(name = "modified_by")
  private String modifiedBy;

  @LastModifiedDate
  @Column(name = "modified_on")
  private LocalDateTime modifiedOn;

  @Column(name = "deleted_by")
  private String deletedBy;

  @Column(name = "deleted_on")
  private LocalDateTime deletedOn;

  @PreUpdate
  @PrePersist
  public void beforeAnyUpdate() {
    if (isDeleted) {

//      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (deletedBy == null/* && authentication != null && authentication.isAuthenticated()*/) {
        deletedBy = "system";
//        deletedBy = authentication.getName();
      } else if (deletedBy == null) {
        deletedBy = "System";
      }
      if (getDeletedOn() == null) {
        deletedOn = LocalDateTime.now();
      }
    }
  }

}