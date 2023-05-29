package net.guildcraft.gceffects;

import net.guildcraft.gceffects.file.DataManager;
import net.guildcraft.gceffects.file.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class GCEffects extends JavaPlugin {
    private static GCEffects instance;
    private FileManager fileManager;
    private DataManager dataManager;

    @Override
    public void onEnable() {
        instance = this;
        fileManager = new FileManager(this);
        dataManager = new DataManager(this);
        saveDefaultConfig();
        fileManager.createDataFile();
        log("Plugin enabled.");
    }

    @Override
    public void onDisable() {
        log("Plugin disabled.");
    }
    public static GCEffects getInstance() { return instance; }
    public FileManager getFileManager() { return fileManager;}
    public DataManager getDataManager() { return dataManager; }
    public void log(String msg) { Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"[GCEffects] "+msg); }
    public static String colourMsg(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }
}
