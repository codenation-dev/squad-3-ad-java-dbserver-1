package br.com.central.erros.impl.business.dto;

import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.entity.enums.Environment;
import br.com.central.erros.impl.business.entity.enums.Level;

import java.time.LocalDate;

public class LogDTOV1 {
    private String ip;
    private Long numberOfEvents;
    private LocalDate date;
    private String title;
    private String details;
    private Environment environment;
    private Level level;
    private UserV1 user;

    public LogDTOV1() {
    }

    public LogDTOV1(String ip, Long numberOfEvents, LocalDate errorDate, String title, String details,
                    Environment environment, Level level, UserV1 user) {
        this.ip = ip;
        this.numberOfEvents = numberOfEvents;
        this.date = errorDate;
        this.title = title;
        this.details = details;
        this.environment = environment;
        this.level = level;
        this.user = user;
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
