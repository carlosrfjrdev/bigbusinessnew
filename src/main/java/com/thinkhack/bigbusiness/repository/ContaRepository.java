package com.thinkhack.bigbusiness.repository;

import com.thinkhack.bigbusiness.model.ContaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContaRepository extends JpaRepository<ContaModel, UUID> {

    Optional<ContaModel> findByAccountUsers (String username);
    boolean existsByAccountUsers(String username);
    boolean existsByAccountName(String accountName);
}
