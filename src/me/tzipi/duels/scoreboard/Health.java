package me.tzipi.duels.scoreboard;

import me.tzipi.duels.Main;
import me.tzipi.duels.manager.GameManager;
import me.tzipi.duels.manager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Health implements Listener {
    private GameManager gameManager;
    public Health(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @SuppressWarnings("deprecation")
    public void setHealthNameTag() {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Team team = board.registerNewTeam("inGame");
        Objective obj = board.registerNewObjective("hp", "hp");
        for(Player inGame : gameManager.inGame)
        {
            obj.setDisplaySlot(DisplaySlot.BELOW_NAME);
            obj.setDisplayName(ChatColor.WHITE + "" + (int)inGame.getHealth() + ChatColor.RED + "❤");
            System.out.println("Added player to team " + inGame.getName());
            inGame.setScoreboard(board);
        }
        System.out.println("Finished");

    }
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        System.out.println("Event called!");
        setHealthNameTag();
    }
         }


