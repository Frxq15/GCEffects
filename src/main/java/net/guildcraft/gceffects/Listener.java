package net.guildcraft.gceffects;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public class Listener implements org.bukkit.event.Listener {
    private GCEffects instance;

    public Listener(GCEffects instance) { this.instance = instance; }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent e) {
        UUID uuid = e.getUniqueId();
    }
}
