package br.com.central.erros.impl.business.dto;

import br.com.central.erros.impl.business.entity.enums.Profile;
import br.com.central.erros.impl.business.entity.enums.UserType;
import br.com.central.erros.impl.business.service.V1.validation.UserInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@UserInsert
public class UserDTOV1 {

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpfOrCnpj;

    private Integer type;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String password;
    private Set<Integer> profiles = new HashSet<>();

    public UserDTOV1() {
        addProfile(Profile.CLIENTE);
    }

    public UserDTOV1(Integer id, String name, String email, String cpfOrCnpj, UserType type, String password) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = (type ==null) ? null : type.getCode();
        this.password = password;
        addProfile(Profile.CLIENTE);
    }

    public UserDTOV1(String name, String email, String cpfOrCnpj, UserType type, String password) {
        super();
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = (type ==null) ? null : type.getCode();
        this.password = password;
        addProfile(Profile.CLIENTE);
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

    public void setProfiles(Set<Integer> profiles) {
        this.profiles = profiles;
    }

    public Set<Integer> getProfiles() {
        return profiles;
    }

    public void addProfile(Profile profile) {
        profiles.add(profile.getCod());
    }

}
