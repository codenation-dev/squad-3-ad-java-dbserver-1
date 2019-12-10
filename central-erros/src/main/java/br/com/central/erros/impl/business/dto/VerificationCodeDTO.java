package br.com.central.erros.impl.business.dto;

public class VerificationCodeDTO {
    private String token;
    private Long id;
    private Integer userId;

    public VerificationCodeDTO() {
    }

    public VerificationCodeDTO(String token, Long id, Integer userId) {
        this.token = token;
        this.id = id;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
