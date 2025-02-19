package com.thinkhack.bigbusiness.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thinkhack.bigbusiness.enums.OperacaoType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_lancamento_conta")
public class LancamentoContaModel extends BaseModel {


    private OperacaoType tipo;
    private LocalDate dataTransacao;
    private BigDecimal valor;
    private String descricao;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private UserModel user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_id")
    @JsonManagedReference
    private ContaModel conta;


}
