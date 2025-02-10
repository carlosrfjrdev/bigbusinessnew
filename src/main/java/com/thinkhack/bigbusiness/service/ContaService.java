package com.thinkhack.bigbusiness.service;

import com.thinkhack.bigbusiness.model.ContaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContaService {


    List<ContaModel> findAll();

    Optional<ContaModel> findById(UUID accountId);

    Optional<ContaModel> findByAccountMaster_Username(String username);

    void delete(ContaModel contaModel);

    void save(ContaModel contaModel);

    ContaModel saveAccountModel(ContaModel contaModel);

    boolean existsByAccountMaster_Username(String username);

    boolean existsByAccountName(String accountName);

}
