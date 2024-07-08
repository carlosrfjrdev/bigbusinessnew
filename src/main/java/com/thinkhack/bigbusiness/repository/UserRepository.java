package com.thinkhack.bigbusiness.repository;

import com.thinkhack.bigbusiness.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UsuarioModel, UUID>  {

    UserDetails findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String username);

}
