package com.java.security.user.repository;

import com.java.security.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Deven Danidhariya
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
