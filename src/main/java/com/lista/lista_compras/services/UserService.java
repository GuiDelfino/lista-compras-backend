package com.lista.lista_compras.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lista.lista_compras.entities.User;
import com.lista.lista_compras.repositories.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User creatUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado!")); 
    }

    public void deleteUser(Long id) {
            userRepository.deleteById(id);
    }

}
