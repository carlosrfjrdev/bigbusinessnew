package com.thinkhack.bigbusiness.controller;

import com.thinkhack.bigbusiness.business.AuthBusiness;
import com.thinkhack.bigbusiness.model.dto.AuthDTO;
import com.thinkhack.bigbusiness.model.dto.ErrorHandleDTO;
import com.thinkhack.bigbusiness.model.dto.NewUserDTO;
import com.thinkhack.bigbusiness.exception.EmailAlreadyExistsException;
import com.thinkhack.bigbusiness.exception.UserAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthBusiness authBusiness;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid NewUserDTO newUserDTO){

        try {
            var status = HttpStatus.CREATED;
            var body = authBusiness.registerUser(newUserDTO);
            return ResponseEntity.status(status).body(body);
        } catch (UserAlreadyExistsException|EmailAlreadyExistsException e ){
            var status = HttpStatus.CONFLICT;
            var body = new ErrorHandleDTO(
                    status.value(),
                    status,
                    e.getClass().getSimpleName(),
                    e.getMessage()
            );
            return ResponseEntity.status(status).body(body);
        } catch (Exception e){
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

    @PostMapping("/signin")
    public ResponseEntity<?> signinUser(@RequestBody @Valid AuthDTO authDTO){

        try {
            var status = HttpStatus.OK;
            var body = authBusiness.login(authDTO);
            return ResponseEntity.status(status).body(body);
        } catch (UsernameNotFoundException e ){
            var status = HttpStatus.NOT_FOUND;
            var body = new ErrorHandleDTO(
                    status.value(),
                    status,
                    e.getClass().getSimpleName(),
                    e.getMessage()
            );
            return ResponseEntity.status(status).body(body);
        } catch (BadCredentialsException e){
            var status = HttpStatus.FORBIDDEN;
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

