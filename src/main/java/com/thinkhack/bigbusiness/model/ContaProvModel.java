package com.thinkhack.bigbusiness.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_conta_prov")
public class ContaProvModel extends BaseModel implements Serializable {


}
