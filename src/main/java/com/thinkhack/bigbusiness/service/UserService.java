package com.thinkhack.bigbusiness.service;

import com.thinkhack.bigbusiness.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserModel> findAll();

    Optional<UserModel> findById(UUID userId);

    Optional<UserModel> findByUsername(String username);

    void delete(UserModel userModel);

    UserModel save(UserModel userModel);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

//    Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);

}
