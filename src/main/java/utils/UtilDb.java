package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilDb {
    private String url;
    private String user;
    private String password;

    public UtilDb(String url, String user, String password) {
        setUrl(url);
        setUser(user);
        setPassword(password);
    }

    public UtilDb() {
    }

    @Override
    public String toString() {
        return "url :" + url + ", user :" + user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnexion() throws SQLException, Exception {
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(getUrl(), getUser(), getPassword());
        } catch (SQLException e) {
            throw new Exception("Connexion echouee :" + e.getMessage());
        }
        return connexion;
    }
}
