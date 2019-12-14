package br.com.central.erros.impl.business.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.central.erros.impl.business.entity.enums.Perfil;
import br.com.central.erros.impl.business.entity.enums.TipoUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTOV1 {

    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;
    private String senha;
    private Set<Integer> perfis = new HashSet<>();

    public UserDTOV1() {
        addPerfil(Perfil.CLIENTE);
    }

    public UserDTOV1(Integer id, String nome, String email, String cpfOuCnpj, TipoUser tipo, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo==null) ? null : tipo.getCod();
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }

    public UserDTOV1(String nome, String email, String cpfOuCnpj, TipoUser tipo, String senha) {
        super();
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo==null) ? null : tipo.getCod();
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public TipoUser getTipo() {
        return TipoUser.toEnum(tipo);
    }

    public void setTipo(TipoUser tipo) {
        this.tipo = tipo.getCod();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPerfis(Set<Integer> perfis) {
        this.perfis = perfis;
    }

    public Set<Integer> getPerfis() {
        return perfis;
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCod());
    }

}
