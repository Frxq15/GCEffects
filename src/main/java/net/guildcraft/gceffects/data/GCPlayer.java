package net.guildcraft.gceffects.data;

import net.guildcraft.gceffects.GCEffects;
import net.guildcraft.gceffects.effect.Effects;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GCPlayer {
    private final UUID uuid;
    private final static Map<UUID, GCPlayer> players = new HashMap();
    private Effects killEffect;

    public GCPlayer(UUID uuid) {
        this.uuid = uuid;
    }
    public void setActiveEffect(Effects effect) {
        this.killEffect = effect;
    }
    public Effects getActiveEffect() {
        return killEffect;
    }
    public static void removePlayerData(UUID uuid) { players.remove(uuid); }
    public static GCPlayer getPlayerData(GCEffects plugin, UUID uuid) {
        if (!players.containsKey(uuid)) {
            GCPlayer playerData = new GCPlayer(uuid);
            playerData.setActiveEffect(Effects.valueOf(plugin.getDataManager().getActiveEffect(uuid)));
        }
        return players.get(uuid);
    }
    public void uploadPlayerData(final GCEffects plugin) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            public void run() {
                plugin.getDataManager().setActiveEffect(uuid, GCPlayer.this.getActiveEffect());
            }
        });
    }
    public static Map<UUID, GCPlayer> getAllPlayerData() {
        return players;
    }
}
