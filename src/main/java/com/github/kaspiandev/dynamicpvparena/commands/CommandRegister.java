package com.github.kaspiandev.dynamicpvparena.commands;

import com.github.kaspiandev.dynamicpvparena.DynamicPVPArena;
import de.themoep.minedown.MineDown;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

import static com.github.kaspiandev.dynamicpvparena.DynamicPVPArena.cacheKit;
import static com.github.kaspiandev.dynamicpvparena.DynamicPVPArena.saveKit;

public class CommandRegister implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                switch (args[0]) {
                    case "savekit" -> {
                        try {
                            saveKit(player);
                            cacheKit();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        player.spigot().sendMessage(new MineDown("[[DynamicPVPArena]](#ff0000-#ed3e3e) [Kit saved](#ffdbdb)").toComponent());
                        }
                    }
                }
            }
        else {
            sender.spigot().sendMessage(new MineDown("[[DynamicPVPArena]](#ff0000-#ed3e3e) [Use /dpa savekit](#ffdbdb))").toComponent());
        }
        return false;
    }
}
