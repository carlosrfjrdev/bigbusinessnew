package com.thinkhack.bigbusiness.controller;

import com.thinkhack.bigbusiness.business.UserBusiness;
import com.thinkhack.bigbusiness.exception.ContaNotFoundErrorException;
import com.thinkhack.bigbusiness.model.dto.ErrorHandleDTO;
import com.thinkhack.bigbusiness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {


    private final UserBusiness userBusiness;

    @GetMapping("/{username}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "username") String username){

        try {
            var status = HttpStatus.OK;
            var body = userBusiness.getUserByUsername(username);
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
            var body = userBusiness.getAll();
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
