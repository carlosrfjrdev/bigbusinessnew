package com.thinkhack.bigbusiness.business.strategy;

import com.thinkhack.bigbusiness.enums.OperacaoType;
import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.model.LancamentoContaModel;
import com.thinkhack.bigbusiness.model.dto.LancamentoDTO;
import com.thinkhack.bigbusiness.service.LancamentoContaService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class LancamentoCreditoStrategy implements LancamentoContaStrategy {


    private LancamentoContaService lancamentoContaService;

    @Override
    public void lancamento(ContaModel contaModel,LancamentoDTO lancamentoDTO) {

        LancamentoContaModel lancamentoFull = new LancamentoContaModel();

        lancamentoFull.setTipo(OperacaoType.CREDITO);
        lancamentoFull.setConta(contaModel);
        lancamentoFull.setDescricao(lancamentoDTO.descricao());
        lancamentoFull.setValor(lancamentoDTO.valor());
        lancamentoFull.setCreated(LocalDateTime.now(ZoneId.of("-03:00")));
        lancamentoFull.setUpdated(LocalDateTime.now(ZoneId.of("-03:00")));
        lancamentoFull.setDataTransacao(LocalDate.now());

        lancamentoContaService.save(lancamentoFull);

    }


}
