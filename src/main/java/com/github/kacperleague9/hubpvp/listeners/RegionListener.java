package com.github.kacperleague9.hubpvp.listeners;

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

import static com.github.kacperleague9.hubpvp.HubPVP.*;

public class RegionListener implements Listener {
    File config = new File(plugin.getDataFolder().getAbsolutePath(), "config.yml");
    FileConfiguration configuration = YamlConfiguration.loadConfiguration(config);
    List<String> list = configuration.getStringList("regions");

    @EventHandler
    public void onEnter(RegionEnterEvent event) throws IOException {
        Player player = event.getPlayer();
        Bukkit.getLogger().info("enter");
        if (list.contains(event.getRegion().getId())) {
            Bukkit.getLogger().info("enter id ok");
            saveInventory(player);
            player.getInventory().clear();
            applyKit(player);
        }
    }
    @EventHandler
    public void onLeave(RegionLeaveEvent event) throws IOException {
        Bukkit.getLogger().info("leave");
        Player player = event.getPlayer();
        if (list.contains(event.getRegion().getId())) {
            Bukkit.getLogger().info("leave id ok");
            restoreInventory(player);
        }
    }

}