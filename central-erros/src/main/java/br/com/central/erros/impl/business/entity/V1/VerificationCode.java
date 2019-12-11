package br.com.central.erros.impl.business.entity.V1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class VerificationCode
{
    @Id
    @GeneratedValue()
    private Long id;

    private String token;

    private String email;

    @JsonCreator
    public VerificationCode(@JsonProperty("id") Long id, @JsonProperty("token") String token,
                            @JsonProperty("email") String email) {
        this.id = id;
        this.token = token;
        this.email = email;
    }

    public VerificationCode(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public VerificationCode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
