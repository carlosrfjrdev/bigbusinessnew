package com.thinkhack.bigbusiness.service;

import com.thinkhack.bigbusiness.model.UsuarioModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {

    List<UsuarioModel> findAll();

    Optional<UsuarioModel> findById(UUID userId);

    Optional<UserDetails> findByUsername(String username);

    void delete(UsuarioModel usuarioModel);

    void save(UsuarioModel usuarioModel);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

//    Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);

}
