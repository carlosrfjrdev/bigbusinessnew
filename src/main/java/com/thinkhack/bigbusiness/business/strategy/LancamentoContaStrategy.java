package com.thinkhack.bigbusiness.business.strategy;

import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.model.dto.LancamentoDTO;

public interface LancamentoContaStrategy {

    void lancamento(ContaModel contaModel, LancamentoDTO lancamentoDTO);
}
