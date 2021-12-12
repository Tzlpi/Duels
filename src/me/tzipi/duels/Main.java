package me.tzipi.duels;

import me.tzipi.duels.SQL.MySQL;
import me.tzipi.duels.SQL.SQLGetter;
import me.tzipi.duels.commands.*;
import me.tzipi.duels.listeners.*;
import me.tzipi.duels.manager.GameManager;
import me.tzipi.duels.scoreboard.Health;
import me.tzipi.duels.scoreboard.HubScoreboard;
import me.tzipi.duels.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.SQLException;


public class Main extends JavaPlugin implements Listener {
    public MySQL SQL;
    public SQLGetter sqlGetter;
    private GameManager gameManager;
    private HubScoreboard hubScoreboard;
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.saveDefaultConfig();
        this.reloadConfig();
        this.SQL = new MySQL(this);
        this.sqlGetter = new SQLGetter(this);
        this.hubScoreboard = new HubScoreboard(this, sqlGetter);
        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getLogger().info(ChatUtils.chat("&4Database is not connected!"));
            Bukkit.getLogger().info(ChatUtils.chat("&4Please connect and reload!"));
        }
        if (SQL.isConnected()) {
            Bukkit.getLogger().info(ChatUtils.chat("&aDatabase is connected!"));
            this.getCommand("addpoint").setExecutor(new AddPoint(sqlGetter));
            sqlGetter.createTable();
            this.getServer().getPluginManager().registerEvents(new HubScoreboard(this, sqlGetter), this);
        }
        Bukkit.getLogger().info(ChatColor.GREEN + "Duels has been enabled");
        this.gameManager = new GameManager(this);

        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new LastHitEvent(gameManager, sqlGetter, this), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(sqlGetter, gameManager, this), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntity(gameManager), this);
        getServer().getPluginManager().registerEvents(new EntityHit(gameManager), this);
        getServer().getPluginManager().registerEvents(new Health(gameManager), this);

        getCommand("join").setExecutor(new Join(gameManager, this));
        getCommand("duels").setExecutor(new ReloadConfig(this));

        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                hubScoreboard.createBoard(online);
            }
        }
    }

    @Override
    public void onDisable() {
        SQL.disconnect();
        super.onDisable();
        Bukkit.getLogger().info(ChatColor.DARK_RED + "Duels has been disabled");

    }
}
