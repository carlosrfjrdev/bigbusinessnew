package com.thinkhack.bigbusiness.business;

import com.thinkhack.bigbusiness.dto.NewUserDTO;
import com.thinkhack.bigbusiness.enums.UserStatus;
import com.thinkhack.bigbusiness.exception.EmailAlreadyExistsException;
import com.thinkhack.bigbusiness.exception.ManageUserException;
import com.thinkhack.bigbusiness.exception.UserAlreadyExistsException;
import com.thinkhack.bigbusiness.model.UserModel;
import com.thinkhack.bigbusiness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;

    public UserModel registerUser(NewUserDTO newUserDTO){
        if (!userService.existsByUsername(newUserDTO.username())){
            throw new UserAlreadyExistsException(String.format("Usuário %s já existe na base",newUserDTO.username()));
        }
        if (!userService.existsByEmail(newUserDTO.email())){
            throw new EmailAlreadyExistsException(String.format("Email %s já existe na base",newUserDTO.email()));
        }
        UserModel userModel = this.newUserConstructor(newUserDTO);
        try {
            userService.save(userModel);
        } catch (Exception e) {
            throw new ManageUserException("Falha ao criar o usuário: "+ e.getMessage());
        }
        return userModel;
    }

    private UserModel newUserConstructor(NewUserDTO newUserDTO) {
        UserModel userModel = new UserModel();

        BeanUtils.copyProperties(newUserDTO, userModel);
        userModel.setStatus(UserStatus.ACTIVE);
        userModel.setCreated(LocalDateTime.now(ZoneId.of("-03:00")));
        userModel.setUpdated(LocalDateTime.now(ZoneId.of("-03:00")));
        String encryptedPass = new BCryptPasswordEncoder().encode(newUserDTO.password());
        userModel.setPassword(encryptedPass);
        userModel.setUserRole(newUserDTO.role());

       return userModel;
    }

    public void updateUser (UserModel userModel){
        if (!userService.existsByUsername(userModel.getUsername())){

        }
        if (!userService.existsByEmail(userModel.setEmail())){

        }


    }


}
