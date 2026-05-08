package com.lista.lista_compras.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lista.lista_compras.entities.User;


@Repository 
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}