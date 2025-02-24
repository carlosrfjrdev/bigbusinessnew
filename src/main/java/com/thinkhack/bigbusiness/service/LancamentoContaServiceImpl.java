package com.thinkhack.bigbusiness.service;

import com.thinkhack.bigbusiness.model.LancamentoContaModel;
import com.thinkhack.bigbusiness.repository.LancamentoContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LancamentoContaServiceImpl implements LancamentoContaService {

    private final LancamentoContaRepository lancamentoContaRepository;

    @Override
    public void save(LancamentoContaModel lancamentoContaModel) {
        lancamentoContaRepository.save(lancamentoContaModel);
    }


}
