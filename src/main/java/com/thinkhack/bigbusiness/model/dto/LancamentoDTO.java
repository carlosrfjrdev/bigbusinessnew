package com.thinkhack.bigbusiness.model.dto;

import com.thinkhack.bigbusiness.enums.OperacaoType;
import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.model.UserModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoDTO(
        OperacaoType tipoLancamento,
        LocalDate data,
        BigDecimal valor,
        String descricao,
        ContaModel conta
) {
}
