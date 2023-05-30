package net.guildcraft.gceffects.data;

import net.guildcraft.gceffects.GCEffects;

import java.util.UUID;

public class DataManager {
    private final GCEffects instance;
    public DataManager(GCEffects instance) {
        this.instance = instance;
    }
    public void createPlayer(UUID uuid) {
        instance.getFileManager().getDataFile().set(uuid + ".ACTIVE_EFFECT", "NONE");
        instance.getFileManager().saveDataFile();
    }
    public String getActiveEffect(UUID uuid) {
        return instance.getFileManager().getDataFile().getString(uuid + ".ACTIVE_EFFECT");
    }
    public void setActiveEffect(UUID uuid, String effect) {
        instance.getFileManager().getDataFile().set(uuid + ".ACTIVE_EFFECT", effect.toUpperCase());
        instance.getFileManager().saveDataFile();
    }
    public boolean playerExists(UUID uuid) {
        return instance.getFileManager().getDataFile().isConfigurationSection(String.valueOf(uuid));
    }
}
