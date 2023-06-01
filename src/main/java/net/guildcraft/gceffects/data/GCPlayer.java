package net.guildcraft.gceffects.data;

import net.guildcraft.gceffects.GCEffects;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GCPlayer {
    private final UUID uuid;
    private final static Map<UUID, GCPlayer> players = new HashMap();
    private String killEffect;
    private String bloodEffect;
    private boolean toggleStatus;
    private boolean bloodEffectStatus;

    public GCPlayer(UUID uuid) {
        this.uuid = uuid;
        players.put(uuid, this);
    }
    public void setActiveEffect(String effect) {
        this.killEffect = effect;
    }
    public String getActiveEffect() {
        return killEffect;
    }
    public void setToggleStatus(boolean status) { this.toggleStatus = status; }
    public boolean getToggleStatus() { return toggleStatus; }

    public void setBloodEffectStatus(boolean status) { this.bloodEffectStatus = status; }
    public boolean getBloodEffectStatus() { return bloodEffectStatus; }
    public void setBloodEffect(String effect) { this.bloodEffect = effect; }
    public String getBloodEffect() { return bloodEffect; }
    public static void removePlayerData(UUID uuid) { players.remove(uuid); }
    public static GCPlayer getPlayerData(GCEffects plugin, UUID uuid) {
        if (!players.containsKey(uuid)) {
            GCPlayer playerData = new GCPlayer(uuid);
            playerData.setActiveEffect(plugin.getDataManager().getActiveEffect(uuid));
            playerData.setToggleStatus(plugin.getDataManager().getToggleStatus(uuid));
            playerData.setBloodEffect(plugin.getDataManager().getActiveBloodEffect(uuid));
            playerData.setBloodEffectStatus(plugin.getDataManager().getBloodEffectStatus(uuid));
        }
        return players.get(uuid);
    }
    public void uploadPlayerData(GCEffects plugin) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> plugin.getDataManager().setActiveEffect(uuid, getActiveEffect()));
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> plugin.getDataManager().setToggleStatus(uuid, getToggleStatus()));
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> plugin.getDataManager().setActiveBloodEffect(uuid, getBloodEffect()));
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> plugin.getDataManager().setBloodEffect(uuid, getBloodEffectStatus()));
    }
    public static Map<UUID, GCPlayer> getAllPlayerData() {
        return players;
    }
}
