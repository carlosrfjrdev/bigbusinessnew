package com.thinkhack.bigbusiness.service;

import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Override
    public List<ContaModel> findAll() {
        return contaRepository.findAll();
    }

    @Override
    public Optional<ContaModel> findById(UUID accountId) {
        return contaRepository.findById(accountId);
    }

    @Override
    public Optional<ContaModel> findByAccountMaster_Username(String username) {
        return Optional.empty();
    }


    @Override
    public void delete(ContaModel contaModel) {
        contaRepository.delete(contaModel);
    }

    @Override
    public void save(ContaModel contaModel) {
        contaRepository.save(contaModel);
    }

    @Override
    public ContaModel saveAccountModel(ContaModel contaModel) {
        return contaRepository.save(contaModel);
    }

    @Override
    public boolean existsByAccountMaster_Username(String username) {
        return false;
    }


    @Override
    public boolean existsByAccountName(String accountName) {
        return contaRepository.existsByAccountName(accountName);
    }
}
