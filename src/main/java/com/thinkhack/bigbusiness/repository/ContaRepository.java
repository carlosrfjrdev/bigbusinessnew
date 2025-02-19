package com.thinkhack.bigbusiness.repository;

import com.thinkhack.bigbusiness.model.ContaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<ContaModel, UUID> {

    boolean existsByNomeDaConta(String accountName);

    Optional<ContaModel> findByUser_Username(String username);
}
