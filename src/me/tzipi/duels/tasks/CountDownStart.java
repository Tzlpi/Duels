package me.tzipi.duels.tasks;

import me.tzipi.duels.Main;
import me.tzipi.duels.manager.GameManager;
import me.tzipi.duels.manager.GameState;
import me.tzipi.duels.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDownStart extends BukkitRunnable {
    private GameManager gameManager;
    private Main plugin;
    public CountDownStart(GameManager gameManager, Main plugin){
        this.gameManager = gameManager;
        this.plugin = plugin;
    }
    private int timeLeft = 6;
    @Override
    public void run() {
        timeLeft--;
        if(timeLeft <= 0) {
            cancel();
            gameManager.setGameState(GameState.ACTIVE);
            return;
        }
        Bukkit.broadcastMessage(ChatColor.YELLOW  + "The game starts in " + ChatColor.RED + timeLeft + ChatColor.YELLOW + " seconds!");


    }
}
