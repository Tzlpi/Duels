package me.tzipi.duels.listeners;

import me.tzipi.duels.Main;
import me.tzipi.duels.SQL.SQLGetter;
import me.tzipi.duels.manager.GameManager;
import me.tzipi.duels.manager.GameState;
import me.tzipi.duels.scoreboard.HubScoreboard;
import me.tzipi.duels.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class LastHitEvent implements Listener {
    private HubScoreboard hubScoreboard;
    private final SQLGetter sqlGetter;
    private final GameManager gameManager;
    private Main main;
    public LastHitEvent(GameManager gameManager, SQLGetter sqlGetter, Main main) {
        this.gameManager = gameManager;
        this.main = main;
        this.sqlGetter = sqlGetter;
    }

    @EventHandler
    public void lastHit(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            Player killer = ((Player) e.getEntity()).getKiller();
            if (e.getFinalDamage() >= player.getHealth()) {
                if (this.gameManager.inGame.contains(player)) {
                    this.gameManager.inGame.remove(player);
                    this.gameManager.inGame.remove(killer);

                    this.gameManager.notInGame.add(player);
                    this.gameManager.notInGame.add(killer);

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes
                    ('&', (main.getConfig().getString("messages.WINNER").replace("%player%",killer.getName()))));

                    Bukkit.broadcastMessage(ChatUtils.chat(main.getConfig().getString("messages.LOST-PLAYER").replace("%player%", player.getName())));
                    player.setHealth(20);
                    killer.setHealth(20);
                    killer.getInventory().clear();
                    player.getInventory().clear();
                    killer.getInventory().setArmorContents(null);
                    player.getInventory().setArmorContents(null);
                    sqlGetter.addPoints(killer.getUniqueId(),1 );
                    sqlGetter.addDeath(player.getUniqueId(),1);
                    this.gameManager.setGameState(GameState.LOBBY);
                    e.setCancelled(true);

                }

            }
        }
    }
}
