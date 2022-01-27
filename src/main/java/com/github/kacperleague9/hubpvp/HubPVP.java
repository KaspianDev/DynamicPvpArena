package com.github.kacperleague9.hubpvp;

import com.github.kacperleague9.hubpvp.commands.CommandRegister;
import com.github.kacperleague9.hubpvp.commands.CommandTabCompleter;
import com.github.kacperleague9.hubpvp.listeners.RegionListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public final class HubPVP extends JavaPlugin {

    public static HubPVP plugin;
    public static Logger logger = Bukkit.getLogger();


    public static void loadConfig(Player p, FileConfiguration c) {
        ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
        p.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
        p.getInventory().setContents(content);
    }

    public static void getInventoryContent(Player p, FileConfiguration c, File f) throws IOException {
        c.set("inventory.armor", p.getInventory().getArmorContents());
        c.set("inventory.content", p.getInventory().getContents());
        c.save(f);
    }

    public static void saveInventory(Player p) throws IOException {
        File f = new File(plugin.getDataFolder().getAbsolutePath() + "/playerdata/", p.getName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        getInventoryContent(p, c, f);
    }

    public static void saveKit(Player p) throws IOException {
        File f = new File(plugin.getDataFolder().getAbsolutePath(), "data.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        getInventoryContent(p, c, f);
    }


    @SuppressWarnings("unchecked")
    public static void restoreInventory(Player p) throws IOException {
        File f = new File(plugin.getDataFolder().getAbsolutePath() + "/playerdata/", p.getName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        loadConfig(p, c);
    }

    @SuppressWarnings("unchecked")
    public static void applyKit(Player p) throws IOException {
        File f = new File(plugin.getDataFolder().getAbsolutePath(), "data.yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        loadConfig(p, c);
    }

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new RegionListener(), this);
        this.getCommand("hubpvp").setExecutor(new CommandRegister());
        this.getCommand("hubpvp").setTabCompleter(new CommandTabCompleter());
        logger.info("[HUBPVP] Loaded.");
    }

    @Override
    public void onDisable() {
        logger.info("[HUBPVP] Disabled.");
    }

}
