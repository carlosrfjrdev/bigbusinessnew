package com.thinkhack.bigbusiness.model;

import com.thinkhack.bigbusiness.enums.ProvisaoType;
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
@Table(name = "tb_conta_prov")
public class ContaProvisionamentoModel extends BaseModel implements Serializable {

    private ContaModel conta;
    private ProvisaoType tipo;
    private ClassificacaoProvisionamentoModel classificacao;
    private LocalDate dataEvento;
    private BigDecimal valor;
    private String descricao;
}
