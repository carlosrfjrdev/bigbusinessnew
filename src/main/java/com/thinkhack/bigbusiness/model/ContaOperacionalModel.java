package com.thinkhack.bigbusiness.model;

import com.thinkhack.bigbusiness.enums.OperacaoType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_conta_ops")
public class ContaOperacionalModel extends BaseModel implements Serializable {

    private ContaModel conta;
    private OperacaoType tipo;
    private LocalDate dataTransacao;
    private BigDecimal valor;
    private String descricao;

}
