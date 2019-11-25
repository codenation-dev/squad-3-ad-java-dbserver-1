package br.com.central.erros.impl.business.entity.V1;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.central.erros.impl.business.entity.enums.Ambiente;
import br.com.central.erros.impl.business.entity.enums.Level;

@Entity
public class LogV1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ip;
    private Long numeroDeEventos;
    private LocalDate dataDoErro;
    private String titulo;
    private String detalhes;

    @Enumerated
    @Column(name = "cod_ambiente_enum")
    private Ambiente ambiente;

    @Enumerated
    @Column(name = "cod_level_enum")
    private Level level;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserV1 userV1;

    public LogV1() {
    }

    public LogV1(Integer id, String ip, Long numeroDeEventos, LocalDate dataDoErro, String titulo, String detalhes, Ambiente ambiente, Level level, UserV1 userV1) {
        this.id = id;
        this.ip = ip;
        this.numeroDeEventos = numeroDeEventos;
        this.dataDoErro = dataDoErro;
        this.titulo = titulo;
        this.detalhes = detalhes;
        this.ambiente = ambiente;
        this.level = level;
        this.userV1 = userV1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public UserV1 getUserV1() {
        return userV1;
    }

    public void setUserV1(UserV1 userV1) {
        this.userV1 = userV1;
    }
}
