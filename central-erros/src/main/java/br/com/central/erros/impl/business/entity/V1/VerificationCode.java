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

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserV1 user;

    @JsonCreator
    public VerificationCode(@JsonProperty("id") Long id, @JsonProperty("token") String token,
                            @JsonProperty("user_id") UserV1 user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }

    public VerificationCode(String token, UserV1 user) {
        this.token = token;
        this.user = user;
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

    public UserV1 getUser() {
        return user;
    }

    public void setUser(UserV1 user) {
        this.user = user;
    }
}
