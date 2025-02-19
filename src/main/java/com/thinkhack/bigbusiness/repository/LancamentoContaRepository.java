package com.thinkhack.bigbusiness.repository;

import com.thinkhack.bigbusiness.model.LancamentoContaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface LancamentoContaRepository extends JpaRepository<LancamentoContaModel, UUID> {

//    BigDecimal saldoContaById(UUID contaID);

}
