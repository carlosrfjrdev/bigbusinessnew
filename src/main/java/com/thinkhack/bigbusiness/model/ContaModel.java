package com.thinkhack.bigbusiness.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_contas")
public class ContaModel extends BaseModel implements Serializable {


    @Size(max = 50)
    @Column(length = 50,unique = true)
    private String accountName;

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<UsuarioModel> accountUsers;


}
