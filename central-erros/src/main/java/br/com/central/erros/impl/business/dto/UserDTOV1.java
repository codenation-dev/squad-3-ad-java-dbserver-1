package br.com.central.erros.impl.business.dto;

public class UserDTOV1 {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Integer token;

    private UserDTOV1(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.email = builder.email;
        this.senha = builder.senha;
        this.token = builder.token;
    }

    public static class Builder {

        private Long id;
        private String nome;
        private String email;
        private String senha;
        private Integer token;

        public Builder() {
        }

        Builder(Long id, String nome, String email, String senha, Integer token) {
            this.id = id;
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.token = token;
        }

        public Builder id(Long id){
            this.id = id;
            return Builder.this;
        }

        public Builder nome(String nome){
            this.nome = nome;
            return Builder.this;
        }

        public Builder email(String email){
            this.email = email;
            return Builder.this;
        }

        public Builder senha(String senha){
            this.senha = senha;
            return Builder.this;
        }

        public Builder token(Integer token){
            this.token = token;
            return Builder.this;
        }

        public UserDTOV1 build() {

            return new UserDTOV1(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }
}
