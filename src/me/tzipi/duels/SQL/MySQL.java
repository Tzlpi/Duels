package me.tzipi.duels.SQL;

import com.avaje.ebeaninternal.server.cluster.Packet;
import me.tzipi.duels.Main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private Main plugin;
    public MySQL(Main plugin) {
        this.plugin = plugin;
    }
    public String port() {
        String port = plugin.getConfig().getString("SQL.port");
        return  port;
    }
    public String host() {
        String host = plugin.getConfig().getString("SQL.host");
        return host;
    }
    public String database() {
        String database = plugin.getConfig().getString("SQL.database");
        return database;
    }
    public String username() {
        String username = plugin.getConfig().getString("SQL.username");
        return username;
    }
    public String password() {
        String password = plugin.getConfig().getString("SQL.password");
        return  password;
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
