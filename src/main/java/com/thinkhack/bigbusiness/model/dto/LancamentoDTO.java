package com.thinkhack.bigbusiness.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkhack.bigbusiness.enums.OperacaoType;
import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.model.UserModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoDTO(
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data,
        @JsonFormat(shape=JsonFormat.Shape.STRING)
        BigDecimal valor,
        String descricao
) {
}
