package com.thinkhack.bigbusiness.business;

import com.thinkhack.bigbusiness.business.strategy.LancamentoContaStrategy;
import com.thinkhack.bigbusiness.business.strategy.LancamentoCreditoStrategy;
import com.thinkhack.bigbusiness.business.strategy.LancementoDebitoStrategy;
import com.thinkhack.bigbusiness.enums.OperacaoType;
import com.thinkhack.bigbusiness.exception.AuthorizationDeniedException;
import com.thinkhack.bigbusiness.exception.ContaNotFoundErrorException;
import com.thinkhack.bigbusiness.model.dto.LancamentoDTO;
import com.thinkhack.bigbusiness.service.ContaService;
import com.thinkhack.bigbusiness.service.LancamentoContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LancamentoContaBusiness {

    private final ContaService contaService;
    private final Map<OperacaoType, LancamentoContaStrategy> mapStrategy = Map.of(
      OperacaoType.DEBITO, new LancementoDebitoStrategy(),
      OperacaoType.CREDITO, new LancamentoCreditoStrategy()
    );

    public void inserirLancamento(String username, Authentication authContext, OperacaoType operacaoType, LancamentoDTO lancamentoDTO){

        if (!username.equalsIgnoreCase(authContext.getName())){
            throw new AuthorizationDeniedException(String.format("Usuário %s não tem autorização de acesso a esta conta",authContext.getName()));
        }

        var conta = contaService.findByUser_Username(username);

        if(conta.isEmpty()){
            throw new ContaNotFoundErrorException(String.format("Conta do usuário %s não encontada", username));
        }

        mapStrategy.get(operacaoType).lancamento(conta.get(), lancamentoDTO);
    }


}
