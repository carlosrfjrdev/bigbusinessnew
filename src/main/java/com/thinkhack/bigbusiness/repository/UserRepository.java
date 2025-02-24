package com.thinkhack.bigbusiness.repository;

import com.thinkhack.bigbusiness.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>  {

    UserModel findByUsername(String username);
    UserDetails findUserDetailsByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String username);

}
