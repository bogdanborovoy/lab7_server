package request;

import models.SpaceMarine;

import java.io.Serializable;

public class Request implements Serializable {

    private String username;
    private String password;
    private String[] args;
    SpaceMarine spaceMarine = null;
    public Request(String username, String password, String[] args) {
        this.username = username;
        this.password = password;
        this.args = args;
    }
    public void addSpaceMarine(SpaceMarine spaceMarine) {
        this.spaceMarine = spaceMarine;
    }
    public String[] getArgs() {
        return args;
    }
    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
