package com.thinkhack.bigbusiness.service;

import com.thinkhack.bigbusiness.model.UsuarioModel;
import com.thinkhack.bigbusiness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UsuarioModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UsuarioModel> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<UserDetails> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public void delete(UsuarioModel usuarioModel) {
        userRepository.delete(usuarioModel);
    }

    @Override
    public void save(UsuarioModel usuarioModel) {
        userRepository.save(usuarioModel);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

//    @Override
//    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
//        return userRepository.findAll(spec,pageable);
//    }
}
