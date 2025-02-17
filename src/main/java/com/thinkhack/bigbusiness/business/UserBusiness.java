package com.thinkhack.bigbusiness.business;

import com.thinkhack.bigbusiness.dto.NewUserDTO;
import com.thinkhack.bigbusiness.exception.EmailAlreadyExistsException;
import com.thinkhack.bigbusiness.exception.ManageUserException;
import com.thinkhack.bigbusiness.exception.UserAlreadyExistsException;
import com.thinkhack.bigbusiness.model.UserModel;
import com.thinkhack.bigbusiness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;

    public UserModel registerUser(NewUserDTO newUserDTO){

        if (userService.existsByUsername(newUserDTO.username())){
            throw new UserAlreadyExistsException(String.format("Usuário %s já existe na base",newUserDTO.username()));
        }

        if (userService.existsByEmail(newUserDTO.email())){
            throw new EmailAlreadyExistsException(String.format("Email %s já existe na base",newUserDTO.email()));
        }

        UserModel userModel = this.newUserConstructor(newUserDTO);

        try {
            userService.save(userModel);
        } catch (Exception e) {
            throw new ManageUserException("Falha ao criar o usuário: "+ e.getMessage());
        }

    }
}
