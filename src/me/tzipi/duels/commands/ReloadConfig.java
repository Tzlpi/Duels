package me.tzipi.duels.commands;

import me.tzipi.duels.Main;
import me.tzipi.duels.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfig implements CommandExecutor {
    private Main main;
    public ReloadConfig (Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("duels")) {
            if (!(sender.isOp())) {
                sender.sendMessage(ChatUtils.chat("&cYou don't have permission to perform this command!"));
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatUtils.chat(main.getConfig().getString("messages.NO-ARG")));
                return true;
            }
            if(args.length > 0  && args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(ChatUtils.chat(main.getConfig().getString("messages.CONFIG-RELOAD")));
                return true;
            }
        }
        return false;
    }
}
