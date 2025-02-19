package com.thinkhack.bigbusiness.business;

import com.thinkhack.bigbusiness.exception.ContaNotFoundErrorException;
import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContaBusiness {
    private final ContaService contaService;

    public ContaModel getUserByUsername(String username) {
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
