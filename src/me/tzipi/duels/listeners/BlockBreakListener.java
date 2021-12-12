package me.tzipi.duels.listeners;

import me.tzipi.duels.Main;
import me.tzipi.duels.manager.GameManager;
import me.tzipi.duels.manager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Arrays;

public class BlockBreakListener implements Listener {
    private GameState gameState;
    private Main plugin;
    public BlockBreakListener(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    private void onBlockBreak(BlockBreakEvent e ) {
        Player player = e.getPlayer();
        if(!(player.isOp())) {
            e.setCancelled(true);
        }else {

            e.setCancelled(false);
            return;
        }
     }
}
