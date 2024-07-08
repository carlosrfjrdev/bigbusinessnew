package com.thinkhack.bigbusiness.controller;

import com.thinkhack.bigbusiness.dto.AuthDTO;
import com.thinkhack.bigbusiness.dto.SigninResponseDTO;
import com.thinkhack.bigbusiness.dto.SingupDTO;
import com.thinkhack.bigbusiness.enums.UserStatus;
import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.model.UsuarioModel;
import com.thinkhack.bigbusiness.security.TokenService;
import com.thinkhack.bigbusiness.service.ContaService;
import com.thinkhack.bigbusiness.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
//@CrossOrigin(origins = "*", maxAge=3600)
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private UsuarioService userService;

    @Autowired
    private ContaService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid SingupDTO userDto){
        if(userService.existsByUsername(userDto.username())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is Already Taken|");
        }
        if(userService.existsByEmail(userDto.email())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: email is Already Taken|");
        }
        if(accountService.existsByAccountMaster_Username(userDto.username())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Account from username is Already Taken|");
        }
        var userModel = new UsuarioModel();
        BeanUtils.copyProperties(userDto,userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setCreated(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
        var encriptedPassword = new BCryptPasswordEncoder().encode(userDto.password());
        userModel.setPassword(encriptedPassword);
        userModel.setUserRole(userDto.role());


        var accountModel = new ContaModel();

        accountModel.setAccountName("Conta do "+userDto.username());
        accountModel.setCreated(LocalDateTime.now(ZoneId.of("UTC")));
        accountModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
        // accountModel.setAccountMaster(userModel);
       // accountModel.setAccountUsers(List.of(userModel));
        userModel.setAccount(accountModel);
        userService.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @PostMapping("/signin")
    public ResponseEntity signinUser(@RequestBody @Valid AuthDTO data){
        var userPass = new UsernamePasswordAuthenticationToken(data.username(),data.password());
        var auth = this.authenticationManager.authenticate(userPass);

        var token = tokenService.generateToken((UsuarioModel) auth.getPrincipal());

        var user = userService.findByUsername(data.username());
        System.out.println("Usuário Logou:"+data.username());

        if (user.isPresent()) {


            return ResponseEntity.status(HttpStatus.OK).body(new SigninResponseDTO(user.get(),token));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }



}

