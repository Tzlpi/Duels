package me.tzipi.duels.scoreboard;

import me.tzipi.duels.Main;
import me.tzipi.duels.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class RemoveScoreboard {
    private GameManager gameManager;
    public RemoveScoreboard(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    public void removeBoard() {
        Bukkit.getOnlinePlayers().stream().filter(player -> this.gameManager.inGame.contains(player)).forEach(this::removeBoard1);
    }

    private void removeBoard1(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }
}
