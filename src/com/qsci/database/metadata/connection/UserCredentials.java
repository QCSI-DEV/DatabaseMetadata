package com.qsci.database.metadata.connection;

public class UserCredentials {
    private String login;
    private String password;
    private String url;
    private String pathToDb;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPathToDb() {
        return pathToDb;
    }

    public void setPathToDb(String pathToDb) {
        this.pathToDb = pathToDb;
    }
}
