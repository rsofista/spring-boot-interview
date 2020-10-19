package br.com.compasso.steffen.lucas.springbootinterview.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.compasso.steffen.lucas.springbootinterview.model.User;
import br.com.compasso.steffen.lucas.springbootinterview.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);

        if (user.isPresent()) {
            return user.get();
        }

        throw new UsernameNotFoundException("Dados inválidos!");
    }
    
}
