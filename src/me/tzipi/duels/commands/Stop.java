package me.tzipi.duels.commands;

import me.tzipi.duels.manager.GameManager;
import me.tzipi.duels.manager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Stop implements CommandExecutor {
    private GameManager gameManager;
    public Stop(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        this.gameManager.setGameState(GameState.LOBBY);
        Bukkit.broadcastMessage("hi");
        return false;
    }
}
