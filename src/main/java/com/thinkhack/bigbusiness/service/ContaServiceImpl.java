package com.thinkhack.bigbusiness.service;

import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;

    @Override
    public List<ContaModel> findAll() {
        return contaRepository.findAll();
    }

    @Override
    public Optional<ContaModel> findById(UUID accountId) {
        return contaRepository.findById(accountId);
    }

    @Override
    public Optional<ContaModel> findByUser_Username(String username) {
        return contaRepository.findByUser_Username(username);
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
    public ContaModel saveContaModel(ContaModel contaModel) {
        return contaRepository.save(contaModel);
    }

    @Override
    public boolean existsByUser_Username(String username) {
        return false;
    }


    @Override
    public boolean existsByNomeDaConta(String nomeDaConta) {
        return contaRepository.existsByNomeDaConta(nomeDaConta);
    }
}
