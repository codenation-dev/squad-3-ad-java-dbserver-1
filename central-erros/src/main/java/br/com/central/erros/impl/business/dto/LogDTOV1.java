package br.com.central.erros.impl.business.dto;

import br.com.central.erros.impl.business.entity.V1.UserV1;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class LogDTOV1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ambiente;
    private int level;
    private String ip;
    private Long numeroDeEventos;
    private LocalDate dataDoErro;
    private String titulo;
    private String detalhes;
    private UserV1 coletor;

    public static class Builder {
        private Long id;
        private int ambiente;
        private int level;
        private String ip;
        private Long numeroDeEventos;
        private LocalDate dataDoErro;
        private String titulo;
        private String detalhes;
        private UserV1 coletor;

        public Builder() {
        }

        Builder(Long id, int ambiente, int level, String ip, Long numeroDeEventos, LocalDate dataDoErro, String titulo, String detalhes, UserV1 coletor) {
            this.id = id;
            this.ambiente = ambiente;
            this.level = level;
            this.ip = ip;
            this.numeroDeEventos = numeroDeEventos;
            this.dataDoErro = dataDoErro;
            this.titulo = titulo;
            this.detalhes = detalhes;
            this.coletor = coletor;
        }

        public Builder id(Long id) {
            this.id = id;
            return Builder.this;
        }

        public Builder ambiente(int ambiente) {
            this.ambiente = ambiente;
            return Builder.this;
        }

        public Builder level(int level) {
            this.level = level;
            return Builder.this;
        }

        public Builder ip(String ip) {
            this.ip = ip;
            return Builder.this;
        }

        public Builder numeroDeEventos(Long numeroDeEventos) {
            this.numeroDeEventos = numeroDeEventos;
            return Builder.this;
        }

        public Builder dataDoErro(LocalDate dataDoErro) {
            this.dataDoErro = dataDoErro;
            return Builder.this;
        }

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return Builder.this;
        }

        public Builder detalhes(String detalhes) {
            this.detalhes = detalhes;
            return Builder.this;
        }

        public Builder coletor(UserV1 coletor) {
            this.coletor = coletor;
            return Builder.this;
        }

        public LogDTOV1 build() {

            return new LogDTOV1(this);
        }
    }

    private LogDTOV1(Builder builder) {
        this.id = builder.id;
        this.ambiente = builder.ambiente;
        this.level = builder.level;
        this.ip = builder.ip;
        this.numeroDeEventos = builder.numeroDeEventos;
        this.dataDoErro = builder.dataDoErro;
        this.titulo = builder.titulo;
        this.detalhes = builder.detalhes;
        this.coletor = builder.coletor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(int ambiente) {
        this.ambiente = ambiente;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getNumeroDeEventos() {
        return numeroDeEventos;
    }

    public void setNumeroDeEventos(Long numeroDeEventos) {
        this.numeroDeEventos = numeroDeEventos;
    }

    public LocalDate getDataDoErro() {
        return dataDoErro;
    }

    public void setDataDoErro(LocalDate dataDoErro) {
        this.dataDoErro = dataDoErro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public UserV1 getColetor() {
        return coletor;
    }

    public void setColetor(UserV1 coletor) {
        this.coletor = coletor;
    }
}

