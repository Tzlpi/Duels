package me.tzipi.duels.commands;

import me.tzipi.duels.Main;
import me.tzipi.duels.manager.GameManager;
import me.tzipi.duels.manager.GameState;
import me.tzipi.duels.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class Join implements CommandExecutor {
    private Main main;
    private GameManager gameManager;
    public Join(GameManager gameManager, Main main) {
        this.main = main;
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("join")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("Console can't perform this command!");
                return false;

            }
            Player player = (Player) sender;
            if(this.gameManager.inGame.contains(player)) {
                player.sendMessage(ChatColor.RED + "You are already in the game!");
                return false;
            }
            if(this.gameManager.inGame.size()==0) {
                this.gameManager.inGame.add(player);
                this.gameManager.notInGame.remove(player);
               // Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + ChatColor.YELLOW +  " has joined (" + ChatColor.AQUA + "1" + ChatColor.YELLOW + "/" +ChatColor.AQUA + "2" +ChatColor.YELLOW +")!"  );
                Bukkit.broadcastMessage(ChatUtils.chat(main.getConfig().getString("messages.JOIN-MESSAGE-FIRST-PLAYER").replace("%player%", player.getName())));
                return false;
            }
           if(this.gameManager.inGame.size() == 1) {
               this.gameManager.inGame.add(player);
              // Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + ChatColor.YELLOW + " has joined (" + ChatColor.AQUA + "2" + ChatColor.YELLOW + "/" +ChatColor.AQUA + "2" +ChatColor.YELLOW +")!");
               Bukkit.broadcastMessage(ChatUtils.chat(main.getConfig().getString("messages.JOIN-MESSAGE-2ND-PLAYER").replace("%player%", player.getName())));
               this.gameManager.notInGame.remove(player);
               gameManager.setGameState(GameState.STARTING);
               return false;
           }

            if(this.gameManager.inGame.size() >= 2) {
                player.sendMessage(ChatColor.RED + "The game is currently full");
                return false;

            }
            }
            if(this.gameManager.gameState == GameState.LOBBY) {
                this.gameManager.setGameState(GameState.STARTING);
                return false;

            }
        return false;
            }

        }






