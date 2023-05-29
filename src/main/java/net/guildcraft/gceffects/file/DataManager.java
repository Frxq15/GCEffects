package net.guildcraft.gceffects.file;

import net.guildcraft.gceffects.GCEffects;
import net.guildcraft.gceffects.effect.Effects;

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
    public void setActiveEffect(UUID uuid, Effects effect) {
        instance.getFileManager().getDataFile().set(uuid + ".ACTIVE_EFFECT", effect.name());
        instance.getFileManager().saveDataFile();
    }
    public boolean playerExists(UUID uuid) {
        return instance.getFileManager().getDataFile().isConfigurationSection(String.valueOf(uuid));
    }
}
