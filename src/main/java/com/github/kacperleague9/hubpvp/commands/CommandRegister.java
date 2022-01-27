package com.github.kacperleague9.hubpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

import static com.github.kacperleague9.hubpvp.HubPVP.logger;
import static com.github.kacperleague9.hubpvp.HubPVP.saveKit;

public class CommandRegister implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                switch (args[0]) {
                    case "savekit" -> {;
                        try {
                            saveKit(player);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        player.sendMessage("[HUBPVP] Kit saved.");
                            logger.info("kit saved");
                        }
                    }
                }
            }
        else {
            sender.sendMessage("[HUBPVP] Use /hubpvp savekit.");
        }
        return false;
    }
}
