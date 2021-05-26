package me.tzipi.duels.tasks;

import me.tzipi.duels.Main;
import me.tzipi.duels.manager.GameManager;
import me.tzipi.duels.manager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TpToLobby {
    private int timeLeftToTp = 6;
    private GameManager gameManager;
    private Main plugin;

    public TpToLobby(GameManager gameManager, Main plugin) {
        this.gameManager = gameManager;
        this.plugin = plugin;
    }

    public void tpToLobby1() {
        Bukkit.getOnlinePlayers().stream().filter(player -> this.gameManager.notInGame.contains(player)).forEach(this::tpToLobby);
    }

    public void tpToLobby(Player player) {
        double x = plugin.getConfig().getDouble("locations.lobby.x");
        double y = plugin.getConfig().getDouble("locations.lobby.y");
        double z = plugin.getConfig().getDouble("locations.lobby.z");
        Location lobby = (Location) player.getLocation();
            lobby.setX(x);
            lobby.setY(y);
            lobby.setZ(z);
            player.teleport(lobby);

    }
}

