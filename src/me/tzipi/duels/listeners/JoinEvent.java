package me.tzipi.duels.listeners;

import me.tzipi.duels.Main;
import me.tzipi.duels.SQL.MySQL;
import me.tzipi.duels.SQL.SQLGetter;
import net.minecraft.server.v1_8_R3.ExpirableListEntry;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinEvent implements Listener {
    public MySQL SQL;
    public SQLGetter data;
    private Main plugin;
    public JoinEvent(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        data.createPlayer(player);
    }
}
