package me.tzipi.duels.listeners;

import me.tzipi.duels.manager.GameManager;
import me.tzipi.duels.manager.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityHit implements Listener {
    private GameManager gameManager;
    public EntityHit(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if(gameManager.gameState == GameState.LOBBY || gameManager.gameState == GameState.STARTING) {
                e.setCancelled(true);
            } else {
                return;
            }
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(gameManager.gameState == GameState.LOBBY) {
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
            }
        }
    }

}
