package br.com.compasso.steffen.lucas.springbootinterview.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.compasso.steffen.lucas.springbootinterview.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
