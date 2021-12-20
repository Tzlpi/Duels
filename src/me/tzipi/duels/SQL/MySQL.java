package me.tzipi.duels.SQL;

import me.tzipi.duels.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private Main plugin;
    public MySQL(Main plugin) {
        this.plugin = plugin;
    }
    public String port() {
        return plugin.getConfig().getString("SQL.port");
    }
    public String host() {
        return plugin.getConfig().getString("SQL.host");
    }
    public String database() {
        return plugin.getConfig().getString("SQL.database");
    }
    public String username() {
        return plugin.getConfig().getString("SQL.username");
    }
    public String password() {
        return plugin.getConfig().getString("SQL.password");
    }
    private Connection connection;

    public boolean isConnected() {
        return(connection == null ? false : true);
    }
    public void connect() throws ClassNotFoundException, SQLException {
        if(!(isConnected())) {
            connection = DriverManager.getConnection("jdbc:mysql://" +
                            host() + ":" + port()+ "/" + database() + "?useSSl=false",
                    username(),password());
        }

    }
    public void disconnect() {
        if(isConnected()) {
            try {
                connection.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    public Connection getConnection () {
        return connection;
    }
}
