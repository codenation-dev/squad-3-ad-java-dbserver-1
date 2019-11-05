package br.com.central.erros.impl.business.dto;

import br.com.central.erros.impl.business.entity.V1.UserV1;

public class UserDTOV1 {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Integer token;

    UserDTOV1(){

    }

    UserDTOV1(Long id, String nome, String email, String senha, Integer token) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.token = token;
    }

    public static UserV1Builder builder(){
        return new UserV1Builder();
    }
    public static class UserV1Builder {

        private Long id;
        private String nome;
        private String email;
        private String senha;
        private Integer token;

        UserV1Builder() {
        }

        public UserV1Builder id(Long id){
            this.id = id;
            return UserV1Builder.this;
        }

        public UserV1Builder nome(String nome){
            this.nome = nome;
            return UserV1Builder.this;
        }

        public UserV1Builder email(String email){
            this.email = email;
            return UserV1Builder.this;
        }

        public UserV1Builder senha(String senha){
            this.senha = senha;
            return UserV1Builder.this;
        }

        public UserV1Builder token(Integer token){
            this.token = token;
            return UserV1Builder.this;
        }

        public UserV1 build() {
            return new UserV1(this.id, this.nome, this.email, this.senha, this.token);
        }
        @Override
        public String toString() {
            return "UserV1.UserV1Builder(id=" + this.id + ", nome=" + this.nome + ", email=" + this.email + ", senha=" + this.senha + ", token=" + this.token + ")";
        }
    }

    @Override
    public String toString() {
        return "UserV1(id=" + this.id + ", nome=" + this.nome + ", email=" + this.email + ", senha=" + this.senha + ", token=" + this.token + ")";
    }

    public void doSomething() {
        // do something
    }
}
