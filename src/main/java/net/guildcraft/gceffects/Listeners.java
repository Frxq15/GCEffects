package net.guildcraft.gceffects;

import net.guildcraft.gceffects.data.DataManager;
import net.guildcraft.gceffects.data.GCPlayer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onLogin(AsyncPlayerPreLoginEvent e) {
        UUID uuid = e.getUniqueId();
        if(!dataManager.playerExists(uuid)) {
            dataManager.createPlayer(uuid);
        }
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(instance, () -> {
            UUID uuid = e.getPlayer().getUniqueId();
            GCPlayer gcPlayer = GCPlayer.getPlayerData(instance, uuid);
            gcPlayer.uploadPlayerData(instance);
            gcPlayer.removePlayerData(uuid);
        });
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player)) return;
        if(!(e.getEntity() instanceof Player)) return;
        Player damager = (Player)e.getDamager();
        Player player = (Player)e.getEntity();

        if(e.getDamage() <= 0) return;
        if(player.getGameMode() == GameMode.CREATIVE) return;

        GCPlayer gcPlayer = GCPlayer.getPlayerData(instance, damager.getUniqueId());

        if(!gcPlayer.getBloodEffectStatus()) return;

        player.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, Material.FIRE);
    }
}
