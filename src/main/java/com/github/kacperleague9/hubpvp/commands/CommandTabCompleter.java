package com.github.kacperleague9.hubpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CommandTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (cmd.getName().equalsIgnoreCase("dynamicpvparena")) {
            List<String> autoCompletes = new ArrayList<>();
                autoCompletes.add("savekit");
                return autoCompletes;
        }
        return null;
    }
}
