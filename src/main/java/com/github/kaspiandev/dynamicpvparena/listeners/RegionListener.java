package com.github.kaspiandev.dynamicpvparena.listeners;

import de.netzkronehd.wgregionevents.events.RegionEnterEvent;
import de.netzkronehd.wgregionevents.events.RegionLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.github.kaspiandev.dynamicpvparena.DynamicPVPArena.*;


public class RegionListener implements Listener {
    File config = new File(plugin.getDataFolder().getAbsolutePath(), "config.yml");
    FileConfiguration configuration = YamlConfiguration.loadConfiguration(config);
    List<String> list = configuration.getStringList("regions");

    @EventHandler
    public void onEnter(RegionEnterEvent event) throws IOException {
        Player player = event.getPlayer();
        Bukkit.getConsoleSender().sendMessage("Player entered region");
        if (list.contains(event.getRegion().getId())) {
            saveInventory(player);
            player.getInventory().clear();
            loadCachedKit(player);
            Bukkit.getConsoleSender().sendMessage("Player entered pvp region");
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