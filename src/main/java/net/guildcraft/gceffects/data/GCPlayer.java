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
    private boolean toggleStatus;
    private boolean bloodEffect;

    public GCPlayer(UUID uuid) {
        this.uuid = uuid;
    }
    public void setActiveEffect(String effect) {
        this.killEffect = effect;
    }
    public String getActiveEffect() {
        return killEffect;
    }
    public void setToggleStatus(boolean status) { this.toggleStatus = status; }
    public boolean getToggleStatus() { return toggleStatus; }
    public void setBloodEffect(boolean status) { this.bloodEffect = status; }
    public boolean getBloodEffect() { return bloodEffect; }
    public static void removePlayerData(UUID uuid) { players.remove(uuid); }
    public static GCPlayer getPlayerData(GCEffects plugin, UUID uuid) {
        if (!players.containsKey(uuid)) {
            GCPlayer playerData = new GCPlayer(uuid);
            playerData.setActiveEffect(plugin.getDataManager().getActiveEffect(uuid));
            playerData.setToggleStatus(plugin.getDataManager().getToggleStatus(uuid));
            playerData.setBloodEffect(plugin.getDataManager().getBloodEffectStatus(uuid));
        }
        return players.get(uuid);
    }
    public void uploadPlayerData(final GCEffects plugin) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            public void run() {
                plugin.getDataManager().setActiveEffect(uuid, getActiveEffect());
                plugin.getDataManager().setToggleStatus(uuid, getToggleStatus());
                plugin.getDataManager().setBloodEffect(uuid, getBloodEffect());
            }
        });
    }
    public static Map<UUID, GCPlayer> getAllPlayerData() {
        return players;
    }
}
