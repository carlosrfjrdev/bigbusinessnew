package com.thinkhack.bigbusiness.controller;

import com.thinkhack.bigbusiness.business.ContaBusiness;
import com.thinkhack.bigbusiness.exception.ContaNotFoundErrorException;
import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.model.dto.ErrorHandleDTO;
import com.thinkhack.bigbusiness.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conta")
public class ContaController {

    private final ContaBusiness contaBusiness;


    @GetMapping("/{username}")
    public ResponseEntity<Object> obeterContaPorUsername(@PathVariable(value = "username") String username){

        try {
            var status = HttpStatus.OK;
            var body = contaBusiness.getUserByUsername(username);
            return ResponseEntity.status(status).body(body);
        } catch (ContaNotFoundErrorException e) {
            var status = HttpStatus.NOT_FOUND;
            var body = new ErrorHandleDTO(
                    status.value(),
                    status,
                    e.getClass().getSimpleName(),
                    e.getMessage()
            );
            return ResponseEntity.status(status).body(body);
        } catch (Exception e ) {
            var status = HttpStatus.INTERNAL_SERVER_ERROR;
            var body = new ErrorHandleDTO(
                    status.value(),
                    status,
                    e.getClass().getSimpleName(),
                    e.getMessage()
            );
            return ResponseEntity.status(status).body(body);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        try {
            var status = HttpStatus.OK;
            var body = contaBusiness.getAll();
            return ResponseEntity.status(status).body(body);
        } catch (Exception e) {
            var status = HttpStatus.NO_CONTENT;
            var body = new ErrorHandleDTO(
                    status.value(),
                    status,
                    e.getClass().getSimpleName(),
                    e.getMessage()
            );
            return ResponseEntity.status(status).body(body);
        }
    }
}
