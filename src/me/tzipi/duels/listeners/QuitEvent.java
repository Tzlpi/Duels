package me.tzipi.duels.listeners;

import me.tzipi.duels.Main;
import me.tzipi.duels.SQL.SQLGetter;
import me.tzipi.duels.manager.GameManager;
import me.tzipi.duels.manager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    private GameManager gameManager;
    private SQLGetter sqlGetter;
    private Main main;
    public QuitEvent(SQLGetter sqlGetter, GameManager gameManager, Main main) {
        this.sqlGetter = sqlGetter;
        this.gameManager = gameManager;
        this.main = main;
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if(gameManager.inGame.contains(player)) {
            gameManager.inGame.remove(player);
        }
        for(Player winner : Bukkit.getOnlinePlayers()) {
            if(gameManager.inGame.contains(winner)) {
                sqlGetter.addPoints(winner.getUniqueId(),1);
                gameManager.notInGame.add(winner);
                gameManager.inGame.remove(winner);
                Bukkit.broadcastMessage(ChatColor.GRAY + "" + player.getName() + ChatColor.RED + " has quit, " + ChatColor.GRAY + winner.getName() + ChatColor.RED + " is the winner");
                winner.getInventory().setArmorContents(null);
                winner.getInventory().clear();
                gameManager.setGameState(GameState.LOBBY);
            }
            if(!(gameManager.inGame.contains(player)) && gameManager.inGame.contains(winner)) {
                return;
            }
        }
    }
}
