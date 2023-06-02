package net.guildcraft.gceffects;

import net.guildcraft.gceffects.command.*;
import net.guildcraft.gceffects.data.DataManager;
import net.guildcraft.gceffects.effect.EffectsRegistry;
import net.guildcraft.gceffects.file.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class GCEffects extends JavaPlugin {
    private static GCEffects instance;
    private FileManager fileManager;
    private DataManager dataManager;
    private EffectsRegistry effectsRegistry;

    @Override
    public void onEnable() {
        instance = this;
        fileManager = new FileManager(this);
        dataManager = new DataManager(this);
        effectsRegistry = new EffectsRegistry();
        saveDefaultConfig();
        getFileManager().createDataFile();
        effectsRegistry.registerEffects();
        Bukkit.getPluginManager().registerEvents(new Listeners(this), this);
        getCommand("testeffect").setExecutor(new testEffectCommand());
        getCommand("testbloodeffect").setExecutor(new testBloodEffectCommand());
        getCommand("togglebloodeffects").setExecutor(new toggleBloodEffectCommand());
        getCommand("setbloodeffect").setExecutor(new setBloodEffectCommand());
        getCommand("setkilleffect").setExecutor(new setKillEffectCommand());
        log("Plugin enabled.");
    }

    @Override
    public void onDisable() {
        log("Plugin disabled.");
    }
    public static GCEffects getInstance() { return instance; }
    public FileManager getFileManager() { return fileManager;}
    public DataManager getDataManager() { return dataManager; }
    public EffectsRegistry getEffectsRegistry() { return effectsRegistry; }

    public void log(String msg) { Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"[GCEffects] "+msg); }
    public String colourize(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }
    public String formatMsg(String msg) { return ChatColor.translateAlternateColorCodes('&', getInstance().getConfig().getString(msg)); }
}
