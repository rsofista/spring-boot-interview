package br.com.compasso.steffen.lucas.springbootinterview.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.steffen.lucas.springbootinterview.dto.TokenDto;
import br.com.compasso.steffen.lucas.springbootinterview.dto.TokenGenerationDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping
    public ResponseEntity<TokenDto> postGenerateToken(@RequestBody @Valid TokenGenerationDto login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());

        try {
            Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            TokenDto token = this.tokenService.generateToken(authentication);

            return ResponseEntity.ok().body(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
