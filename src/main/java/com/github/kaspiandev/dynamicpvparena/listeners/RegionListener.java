package com.github.kaspiandev.dynamicpvparena.listeners;

import static com.github.kaspiandev.dynamicpvparena.DynamicPVPArena.loadCachedKit;
import static com.github.kaspiandev.dynamicpvparena.DynamicPVPArena.plugin;
import static com.github.kaspiandev.dynamicpvparena.DynamicPVPArena.restoreInventory;
import static com.github.kaspiandev.dynamicpvparena.DynamicPVPArena.saveInventory;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.netzkronehd.wgregionevents.events.RegionEnteredEvent;
import de.netzkronehd.wgregionevents.events.RegionLeaveEvent;


public class RegionListener implements Listener {
    File config = new File(plugin.getDataFolder().getAbsolutePath(), "config.yml");
    FileConfiguration configuration = YamlConfiguration.loadConfiguration(config);
    List<String> list = configuration.getStringList("regions");

    @EventHandler
    public void onEnter(RegionEnteredEvent event) throws IOException {
        Player player = event.getPlayer();
        if (list.contains(event.getRegion().getId())) {
            saveInventory(player);
            player.getInventory().clear();
            loadCachedKit(player);
        }
    }

    @EventHandler
    public void onLeave(RegionLeaveEvent event) {
        Player player = event.getPlayer();
        if (list.contains(event.getRegion().getId())) {
            restoreInventory(player);
        }
    }
}
