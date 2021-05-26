package me.tzipi.duels.commands;

import me.tzipi.duels.Main;
import me.tzipi.duels.SQL.MySQL;
import me.tzipi.duels.SQL.SQLGetter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddPoint implements CommandExecutor {

    private SQLGetter sqlGetter;
    public AddPoint(SQLGetter sqlGetter) {
        this.sqlGetter = sqlGetter;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        sqlGetter.addPoints(player.getUniqueId(),1);
        player.sendMessage("point added");
        return false;
    }
}
