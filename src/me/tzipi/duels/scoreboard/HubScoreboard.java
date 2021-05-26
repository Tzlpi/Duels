package me.tzipi.duels.scoreboard;

import me.tzipi.duels.Main;
import me.tzipi.duels.SQL.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.*;

public class HubScoreboard implements Listener {
    private SQLGetter sqlGetter;
    private Main plugin;

    public HubScoreboard(Main plugin, SQLGetter sqlGetter) {
        this.sqlGetter = sqlGetter;
        this.plugin = plugin;
    }

    public void createBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective(ChatColor.YELLOW + "DUELS", "Dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score line2 = obj.getScore(ChatColor.AQUA + "This is a duels");
        line2.setScore(3);

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore(ChatColor.AQUA + "plugin made by Tzipi!");
        score.setScore(2);

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score emptyLine = obj.getScore("");
        emptyLine.setScore(1);

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score wins = obj.getScore(ChatColor.WHITE + "Wins: " + ChatColor.YELLOW + sqlGetter.getPoints(player.getUniqueId()));
        wins.setScore(0);
        player.setScoreboard(board);
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        sqlGetter.createPlayer(p);
        createBoard(e.getPlayer());
    }
}
