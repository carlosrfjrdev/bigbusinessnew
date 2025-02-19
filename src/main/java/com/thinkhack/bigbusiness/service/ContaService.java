package com.thinkhack.bigbusiness.service;

import com.thinkhack.bigbusiness.model.ContaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContaService {


    List<ContaModel> findAll();

    Optional<ContaModel> findById(UUID accountId);

    Optional<ContaModel> findByUser_Username(String username);

    void delete(ContaModel contaModel);

    void save(ContaModel contaModel);

    ContaModel saveContaModel(ContaModel contaModel);

    boolean existsByUser_Username(String username);

    boolean existsByNomeDaConta(String nomeDaConta);

}
