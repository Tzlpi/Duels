package me.tzipi.duels.tasks;

import me.tzipi.duels.Main;
import me.tzipi.duels.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class TpToTeamLocation {
    ArrayList<Location> spawns = new ArrayList<Location>() {
    };
    ArrayList<Location> takenSpawns = new ArrayList<Location>() {
    };
    private GameManager gameManager;
    private Main plugin;
    public TpToTeamLocation (GameManager gameManager, Main plugin) {
        this.gameManager = gameManager;
        this.plugin = plugin;
    }
    public void TpToSpawn1() {
        Bukkit.getOnlinePlayers().stream().filter(player -> this.gameManager.inGame.contains(player)).forEach(this::TpToSpawn);
    }
    public void TpToSpawn(Player player) {
        Random random = new Random();
        for(Player p : Bukkit.getOnlinePlayers()) {
            Location loc1 = (Location) p.getLocation();
            double x1= plugin.getConfig().getDouble("locations.spawn_1.x");
            double x2= plugin.getConfig().getDouble("locations.spawn_2.x");
            double y1 = plugin.getConfig().getDouble("locations.spawn_1.y");
            double y2 = plugin.getConfig().getDouble("locations.spawn_2.y");
            double z1 = plugin.getConfig().getDouble("locations.spawn_1.z");
            double z2 = plugin.getConfig().getDouble("locations.spawn_2.z");
            loc1.setX(x1);
            loc1.setY(y1);
            loc1.setZ(z1);
            Location loc2 = (Location) p.getLocation();
            loc2.setX(x2);
            loc2.setY(y2);
            loc2.setZ(z2);

            spawns.add(loc1);
            spawns.add(loc2);

            Location spawn1 = spawns.get(random.nextInt(spawns.size()));

            if(!(takenSpawns.contains(loc1))) {
                takenSpawns.add(loc1);
                player.teleport(loc1);
            } else {
                player.teleport(loc2);
                takenSpawns.add(loc2);
            }
        }
    }
}
