package tn.projetdemo.demo.dto;

import lombok.Data;
import tn.projetdemo.demo.entities.User;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private User user; // Ajout du champ User

    // Constructeurs
    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthResponseDTO(String accessToken, User user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    // Getters et setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
