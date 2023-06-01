package net.guildcraft.gceffects;

import net.guildcraft.gceffects.data.DataManager;
import net.guildcraft.gceffects.data.GCPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player)) return;
        if(!(e.getEntity() instanceof Player)) return;
        Player damager = (Player)e.getDamager();
        Player player = (Player)e.getEntity();

        GCPlayer gcPlayer = GCPlayer.getPlayerData(instance, damager.getUniqueId());

        if(!gcPlayer.getBloodEffect()) return;

        player.getWorld().playEffect(player.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
    }
}
