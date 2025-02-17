package com.thinkhack.bigbusiness.controller;

import com.thinkhack.bigbusiness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge=3600)
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{username}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "username") String username){

        Optional<UserDetails> userDetailsOptional = userService.findByUsername(username);
        if(!userDetailsOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } else {
            return  ResponseEntity.status(HttpStatus.OK).body(userDetailsOptional.get());
        }
    }
}
