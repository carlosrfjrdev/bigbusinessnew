package com.thinkhack.bigbusiness.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_classif_prov")
public class ClassificacaoProvisionamentoModel extends BaseModel implements Serializable {

    private String classificacao;

}
