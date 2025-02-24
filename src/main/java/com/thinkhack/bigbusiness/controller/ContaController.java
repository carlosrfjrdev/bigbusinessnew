package com.thinkhack.bigbusiness.controller;

import com.thinkhack.bigbusiness.business.ContaBusiness;
import com.thinkhack.bigbusiness.business.LancamentoContaBusiness;
import com.thinkhack.bigbusiness.enums.OperacaoType;
import com.thinkhack.bigbusiness.exception.ContaNotFoundErrorException;
import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.model.dto.ErrorHandleDTO;
import com.thinkhack.bigbusiness.model.dto.LancamentoDTO;
import com.thinkhack.bigbusiness.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conta")
public class ContaController {

    private final ContaBusiness contaBusiness;
    private final LancamentoContaBusiness lancamentoContaBusiness;


    @GetMapping("/{username}")
    public ResponseEntity<Object> obeterContaPorUsername(@PathVariable(value = "username") String username) {
        var authContext = SecurityContextHolder.getContext().getAuthentication();
        try {
            var status = HttpStatus.OK;
            var body = contaBusiness.getUserByUsername(username, authContext);
            return ResponseEntity.status(status).body(body);
        } catch (AuthorizationDeniedException e ){
            var status = HttpStatus.UNAUTHORIZED;
            var body = new ErrorHandleDTO(
                    status.value(),
                    status,
                    e.getClass().getSimpleName(),
                    e.getMessage()
            );
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
    @PostMapping("/{username}/lancamento/{operacaoType}")
    public ResponseEntity<?> realizarLancamento(
            @PathVariable(value = "username") String username,
            @PathVariable(value = "operacaoType") OperacaoType operacaoType,
            @RequestBody @Valid LancamentoDTO lancamentoDTO) {
        var authContext = SecurityContextHolder.getContext().getAuthentication();
        try {
            var status = HttpStatus.CREATED;
            lancamentoContaBusiness.inserirLancamento(username, authContext, operacaoType, lancamentoDTO);
            var body = contaBusiness.getUserByUsername(username, authContext);
            return ResponseEntity.status(status).body(body);
        } catch (AuthorizationDeniedException e ){
            var status = HttpStatus.UNAUTHORIZED;
            var body = new ErrorHandleDTO(
                    status.value(),
                    status,
                    e.getClass().getSimpleName(),
                    e.getMessage()
            );
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
