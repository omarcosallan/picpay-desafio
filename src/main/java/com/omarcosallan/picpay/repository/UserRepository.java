package com.omarcosallan.picpay.repository;

import com.omarcosallan.picpay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    Optional<User> findByCpf(String cpf);
}
