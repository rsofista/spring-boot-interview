package br.com.compasso.steffen.lucas.springbootinterview.dto;

public class TokenDto {
    private String token;
    private String type;
    private Long expiration;

    public TokenDto(String token, String type, Long expiration) {
        this.token = token;
        this.type = type;
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public Long getExpiration() {
        return expiration;
    }
}
