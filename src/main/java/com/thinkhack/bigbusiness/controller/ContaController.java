package com.thinkhack.bigbusiness.controller;

import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge=3600)
@RequestMapping("/v1/account")
public class ContaController {

    @Autowired
    private ContaService accountService;

    @GetMapping("/{username}")
    public ResponseEntity<Object> getAccountByMasterUsername(@PathVariable(value = "username") String username){

        Optional<ContaModel> accountModelOptional = accountService.findByAccountMaster_Username(username);

        if(accountModelOptional.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found.");
        } else {
            return  ResponseEntity.status(HttpStatus.OK).body(accountModelOptional.get());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllAccounts(){


        var accounts = accountService.findAll();

        if(accounts.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found.");
        } else {
            return  ResponseEntity.status(HttpStatus.OK).body(accounts);
        }
    }

}
