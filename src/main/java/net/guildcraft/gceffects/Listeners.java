package net.guildcraft.gceffects;

import net.guildcraft.gceffects.data.DataManager;
import net.guildcraft.gceffects.data.GCPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class Listeners implements org.bukkit.event.Listener {
    private GCEffects instance;
    private DataManager dataManager;

    public Listeners(GCEffects instance) {
        this.instance = instance;
        this.dataManager = instance.getDataManager();
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent e) {
        UUID uuid = e.getUniqueId();
        if(!dataManager.playerExists(uuid)) {
            dataManager.createPlayer(uuid);
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(instance, () -> {
            UUID uuid = e.getPlayer().getUniqueId();
            GCPlayer gcPlayer = GCPlayer.getPlayerData(instance, uuid);
            gcPlayer.uploadPlayerData(instance);
            gcPlayer.removePlayerData(uuid);
        });
    }
}
