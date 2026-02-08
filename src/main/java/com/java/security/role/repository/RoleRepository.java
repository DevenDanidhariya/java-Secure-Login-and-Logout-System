package com.java.security.role.repository;

import com.java.security.role.model.Role;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Deven Danidhariya
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByIdAndIsDeleted(Long id, boolean isDeleted);

  Optional<Role> findByNameAndIsDeleted(String name, boolean isDeleted);

  List<Role> findAllByIsDeleted(boolean isDeleted);

  @Query("""
      SELECT r from Role r
      WHERE r.id IN :roleIds
      AND r.isDeleted = :isDeleted
      """)
  Set<Role> findRolesByIdsAndIsDeleted(Set<Long> roleIds, boolean isDeleted);

}
