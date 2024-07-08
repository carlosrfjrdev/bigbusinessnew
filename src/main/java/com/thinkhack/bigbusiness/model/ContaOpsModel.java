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
public class ContaOpsModel extends BaseModel implements Serializable {

    private ContaModel account;
    private OperacaoType type;
    private LocalDate postDate;
    private BigDecimal value;
    private String description;

}
