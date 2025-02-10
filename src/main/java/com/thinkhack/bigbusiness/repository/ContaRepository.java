package com.thinkhack.bigbusiness.repository;

import com.thinkhack.bigbusiness.model.ContaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContaRepository extends JpaRepository<ContaModel, UUID> {

    Optional<ContaModel> findByUsuariosDaConta (String username);
    boolean existsByUsuariosDaConta (String username);
    boolean existsByNomeDaConta(String accountName);
}
