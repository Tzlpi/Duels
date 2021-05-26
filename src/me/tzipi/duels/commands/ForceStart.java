package me.tzipi.duels.commands;

import me.tzipi.duels.manager.GameManager;
import me.tzipi.duels.manager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ForceStart implements CommandExecutor {
private GameManager gameManager;
public ForceStart(GameManager gameManager) {
    this.gameManager = gameManager;
}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    gameManager.setGameState(GameState.STARTING);

        return false;
    }
}
