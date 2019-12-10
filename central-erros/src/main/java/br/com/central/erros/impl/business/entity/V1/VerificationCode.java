package br.com.central.erros.impl.business.entity.V1;

import javax.persistence.*;

@Entity
public class VerificationCode
{
    @Id
    @GeneratedValue()
    private long id;

    private String token;

    public VerificationCode(String token, UserV1 user) {
        this.token = token;
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private UserV1 user;

    public VerificationCode(long id, String token, UserV1 user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
