package br.com.central.erros.impl.business.entity.V1;

import br.com.central.erros.impl.business.entity.enums.Environment;
import br.com.central.erros.impl.business.entity.enums.Level;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class LogV1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ip;
    private Long numberOfEvents;
    private LocalDate date;
    private String title;
    private String details;

    @Enumerated
    @Column(name = "cod_ambiente_enum")
    private Environment environment;

    @Enumerated
    @Column(name = "cod_level_enum")
    private Level level;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserV1 user;

    public LogV1() {
    }

    public LogV1(Integer id, String ip, Long numberOfEvents, LocalDate dataDoErro, String title, String detalhes, Environment environment, Level level, UserV1 user) {
        this.id = id;
        this.ip = ip;
        this.numberOfEvents = numberOfEvents;
        this.date = dataDoErro;
        this.title = title;
        this.details = detalhes;
        this.environment = environment;
        this.level = level;
        this.user = user;
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

    public Long getNumberOfEvents() {
        return numberOfEvents;
    }

    public void setNumberOfEvents(Long numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public UserV1 getUser() {
        return user;
    }

    public void setUser(UserV1 user) {
        this.user = user;
    }
}
