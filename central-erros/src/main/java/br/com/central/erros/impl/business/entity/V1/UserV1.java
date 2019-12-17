package br.com.central.erros.impl.business.entity.V1;

import br.com.central.erros.impl.business.entity.enums.Profile;
import br.com.central.erros.impl.business.entity.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class UserV1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique=true)
    private String email;

    private String cpfOrCnpj;

    private Integer type;

    @JsonIgnore
    private String password;

    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(name="PERFIS")
    private Set<Integer> profile = new HashSet<>();

    public UserV1() {
        addPerfil(Profile.CLIENTE);
    }

    public UserV1(Integer id, String nome, String email, String cpfOrCnpj, UserType tipo, String senha) {
        super();
        this.id = id;
        this.name = nome;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = (tipo==null) ? null : tipo.getCode();
        this.password = senha;
        addPerfil(Profile.CLIENTE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public UserType getType() {
        return UserType.toEnum(type);
    }

    public void setType(UserType type) {
        this.type = type.getCode();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(Set<Integer> profile) {
        this.profile = profile;
    }

    public Set<Profile> getProfile() {
        return profile.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Profile profile) {
        this.profile.add(profile.getCod());
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserV1 other = (UserV1) obj;
        if (id == null) {
            return other.id == null;
        } else
            return id.equals(other.id);
    }
}
