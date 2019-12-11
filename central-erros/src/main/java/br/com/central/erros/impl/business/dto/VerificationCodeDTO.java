package br.com.central.erros.impl.business.dto;

public class VerificationCodeDTO {
    private String token;
    private String email;

    public VerificationCodeDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VerificationCodeDTO(String token, String email) {
        this.token = token;
        this.email = email;
    }
}
