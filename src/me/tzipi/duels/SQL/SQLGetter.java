package me.tzipi.duels.SQL;

import me.tzipi.duels.Main;
import me.tzipi.duels.commands.AddPoint;
import me.tzipi.duels.listeners.LastHitEvent;
import me.tzipi.duels.listeners.QuitEvent;
import me.tzipi.duels.manager.GameManager;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {
    private AddPoint addPoint;
    private QuitEvent quitEvent;
    private Main plugin;

    public SQLGetter(Main plugin) {
        this.plugin = plugin;
        AddPoint addPoint = new AddPoint(this);
    }
    public void createTable() {
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS duels " +
                    "(NAME VARCHAR(100),UUID VARCHAR(100), POINTS INT(100), PRIMARY KEY(NAME), DEATHS INT(100))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createPlayer(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            if(!exists(uuid)) {
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO duels" +
                        "(NAME, UUID) VALUES(?, ?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.executeUpdate();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public boolean exists(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM duels WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if(results.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addPoints(UUID uuid, int points) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE duels SET POINTS=? WHERE UUID=?");
            ps.setInt(1,(getPoints(uuid) + points));
            ps.setString(2,uuid.toString());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addDeath(UUID uuid, int deaths) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE duels SET DEATHS=? WHERE UUID=?");
            ps.setInt(1,(getDeaths(uuid) + deaths));
            ps.setString(2,uuid.toString());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getDeaths(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT DEATHS FROM duels WHERE UUID=?");
            ps.setString(1,uuid.toString());
            ResultSet rs = ps.executeQuery();
            int deaths = 0;
            if(rs.next()) {
                deaths = rs.getInt("DEATHS");
                return deaths;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPoints(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT POINTS FROM duels WHERE UUID=?");
            ps.setString(1,uuid.toString());
            ResultSet rs = ps.executeQuery();
            int points = 0;
            if(rs.next()) {
                points = rs.getInt("POINTS");
                return points;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
