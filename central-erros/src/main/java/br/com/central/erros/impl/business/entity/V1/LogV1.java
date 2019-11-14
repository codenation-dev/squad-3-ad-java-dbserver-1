package br.com.central.erros.impl.business.entity.V1;

import br.com.central.erros.impl.business.enums.Ambiente;
import br.com.central.erros.impl.business.enums.Level;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class LogV1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Ambiente ambiente;
    private Level level;
    private String ip;
    private Long numeroDeEventos;
    private LocalDate dataDoErro;
    private String titulo;
    private String detalhes;
    private UserV1 coletor;

    public static class Builder {
        private Long id;
        private Ambiente ambiente;
        private Level level;
        private String ip;
        private Long numeroDeEventos;
        private LocalDate dataDoErro;
        private String titulo;
        private String detalhes;
        private UserV1 coletor;

        public Builder() {
        }

        Builder(Long id, Ambiente ambiente, Level level, String ip, Long numeroDeEventos, LocalDate dataDoErro, String titulo, String detalhes, UserV1 coletor) {
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

        public Builder ambiente(Ambiente ambiente) {
            this.ambiente = ambiente;
            return Builder.this;
        }

        public Builder level(Level level) {
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

        public LogV1 build() {

            return new LogV1(this);
        }
    }

    private LogV1(Builder builder) {
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

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
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

