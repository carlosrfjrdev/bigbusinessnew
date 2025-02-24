package com.thinkhack.bigbusiness.business;

import com.thinkhack.bigbusiness.exception.AuthorizationDeniedException;
import com.thinkhack.bigbusiness.exception.ContaNotFoundErrorException;
import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.service.ContaService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContaBusiness {
    private final ContaService contaService;

    public ContaModel getUserByUsername(String username, Authentication authContext) {

        if (!username.equalsIgnoreCase(authContext.getName())){
            throw new AuthorizationDeniedException(String.format("Usuário %s não tem autorização de acesso a esta conta",authContext.getName()));
        }

        Optional<ContaModel> conta = contaService.findByUser_Username(username);

        if(conta.isEmpty()){
          throw new ContaNotFoundErrorException(String.format("Conta do usuário %s não encontada", username));
        }

        return conta.get();
    }

    public List<ContaModel> getAll(){
        return  contaService.findAll();
    }

}
