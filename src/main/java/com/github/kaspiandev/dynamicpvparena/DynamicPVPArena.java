package com.github.kaspiandev.dynamicpvparena;

import com.github.kaspiandev.dynamicpvparena.commands.CommandRegister;
import com.github.kaspiandev.dynamicpvparena.commands.CommandTabCompleter;
import com.github.kaspiandev.dynamicpvparena.listeners.RegionListener;
import de.themoep.minedown.MineDown;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class DynamicPVPArena extends JavaPlugin {

    public static DynamicPVPArena plugin;
    public static ItemStack[] cachedKitArmor;
    public static ItemStack[] cachedKitInventory;
    public static File dataFile;


    public static void cacheKit() {
        File f = dataFile;
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        cachedKitArmor = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
        cachedKitInventory = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
    }

    public static void loadCachedKit(Player p) {
        p.getInventory().setArmorContents(cachedKitArmor);
        p.getInventory().setContents(cachedKitInventory);
    }

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
        File f = dataFile;
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        getInventoryContent(p, c, f);
    }


    public static void restoreInventory(Player p) {
        File f = new File(plugin.getDataFolder().getAbsolutePath() + "/playerdata/", p.getName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        loadConfig(p, c);
    }

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new RegionListener(), this);
        Objects.requireNonNull(this.getCommand("dynamicpvparena")).setExecutor(new CommandRegister());
        Objects.requireNonNull(this.getCommand("dynamicpvparena")).setTabCompleter(new CommandTabCompleter());
        if (!new File(plugin.getDataFolder().getAbsolutePath(), "data.yml").exists()) {
            plugin.saveResource("data.yml", false);
            Bukkit.getConsoleSender().spigot().sendMessage(new MineDown("[[DynamicPVPArena]](#ff0000-#ed3e3e) [Kit not found, using default one](#ffdbdb)").toComponent());
        }
        plugin.saveDefaultConfig();
        dataFile = new File(plugin.getDataFolder().getAbsolutePath(), "data.yml");
        cacheKit();
		Bukkit.getConsoleSender().spigot().sendMessage(new MineDown("[[DynamicPVPArena]](#ff0000-#ed3e3e) [Loaded :)](#ffdbdb)").toComponent());
    }

    @Override
    public void onDisable() {
        plugin.saveConfig();
        Bukkit.getConsoleSender().spigot().sendMessage(new MineDown("[[DynamicPVPArena]](#ff0000-#ed3e3e) [Disabled](#ffdbdb)").toComponent());
    }

}
