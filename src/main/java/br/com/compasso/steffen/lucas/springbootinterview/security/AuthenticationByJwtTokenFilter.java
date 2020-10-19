package br.com.compasso.steffen.lucas.springbootinterview.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.compasso.steffen.lucas.springbootinterview.controller.TokenService;
import br.com.compasso.steffen.lucas.springbootinterview.model.User;
import br.com.compasso.steffen.lucas.springbootinterview.repository.UserRepository;

public class AuthenticationByJwtTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UserRepository userRepository;

    public AuthenticationByJwtTokenFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        String token = this.getTokenFromRequest(request);
        boolean tokenValid = this.tokenService.isTokenValid(token);

        if (tokenValid) {
            this.authenticateUser(token);
        }

        System.out.println("|"+token+"|"+tokenValid);
        filterChain.doFilter(request, response);
    }

    private void authenticateUser(String token) {
        Long userId = this.tokenService.getUserId(token);

        User user = this.userRepository.findById(userId).get();

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
            return null;
        }

        return header.substring("Bearer ".length(), header.length());
    }
    
}
