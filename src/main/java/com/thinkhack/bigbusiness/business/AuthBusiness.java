package com.thinkhack.bigbusiness.business;

import com.thinkhack.bigbusiness.model.dto.AuthDTO;
import com.thinkhack.bigbusiness.model.dto.AuthResponseDTO;
import com.thinkhack.bigbusiness.model.dto.NewUserDTO;
import com.thinkhack.bigbusiness.enums.UserStatus;
import com.thinkhack.bigbusiness.exception.EmailAlreadyExistsException;
import com.thinkhack.bigbusiness.exception.UserAlreadyExistsException;
import com.thinkhack.bigbusiness.model.ContaModel;
import com.thinkhack.bigbusiness.model.UserModel;
import com.thinkhack.bigbusiness.security.TokenService;
import com.thinkhack.bigbusiness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class AuthBusiness {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserModel registerUser(NewUserDTO newUserDTO){
        if(userService.existsByUsername(newUserDTO.username())){
            throw new UserAlreadyExistsException(String.format("Nome de usuário %s já existe na base",newUserDTO.username()));
        }
        if(userService.existsByEmail(newUserDTO.email())){
            throw new EmailAlreadyExistsException(String.format("Email %s já existe na base", newUserDTO.email()));
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(newUserDTO,userModel);
        userModel.setStatus(UserStatus.ACTIVE);
        userModel.setCreated(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setPassword(new BCryptPasswordEncoder().encode(newUserDTO.password()));
        userModel.setUserRole(newUserDTO.role());


        var contaModel = new ContaModel();

        contaModel.setNomeDaConta("Conta do "+newUserDTO.username());
        contaModel.setCreated(LocalDateTime.now(ZoneId.of("UTC")));
        contaModel.setUpdated(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setConta(contaModel);
        return userService.save(userModel);
    }

    public AuthResponseDTO login(AuthDTO authDTO) throws BadCredentialsException {
        var userPass = new UsernamePasswordAuthenticationToken(authDTO.username(),authDTO.password());
        var auth = this.authenticationManager.authenticate(userPass);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        var user = userService.findByUsername(authDTO.username());
        if (user.isPresent()) {
            return new AuthResponseDTO(user.get(),token);
        } else {
            throw new UsernameNotFoundException(String.format("Usuário %s não identificado",authDTO.username()));
        }
    }
}
