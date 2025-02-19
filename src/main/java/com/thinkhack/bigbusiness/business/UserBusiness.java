package com.thinkhack.bigbusiness.business;

import com.thinkhack.bigbusiness.enums.UserStatus;
import com.thinkhack.bigbusiness.exception.UserErrorException;
import com.thinkhack.bigbusiness.model.UserModel;
import com.thinkhack.bigbusiness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;

    public UserModel getUserByUsername(String username){
        var user = userService.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usuário %s não encontrado", username));
        }
        return user.get();

    }
    public UserModel getUserById(String uuid){
        UUID id;

        try{
            id = UUID.fromString(uuid);
        } catch (Exception e){
            throw new UserErrorException(String.format("ID %s é inválido",uuid));
        }
        var user = userService.findById(id);


        if (user.isEmpty()){
            throw new UserErrorException(String.format("Usuário com ID %s não encontrado", uuid));
        }

        return user.get();

    }

    public void inactiveUser(String username){
        var user = userService.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usuário %s não encontrado", username));
        }

        user.get().setStatus(UserStatus.INACTIVE);
        user.get().setUpdated(LocalDateTime.now(ZoneId.of("-03:00")));

        userService.save(user.get());

    }

    public void updateUser(UserModel userModel){
        //TODO: Criar updates
    }
    public void updateUserPass(UserModel userModel,String pass, String matchesPass){
        //TODO: Criar updates Pass
    }

    public List<UserModel> getAll(){

        return userService.findAll();
    }


}
