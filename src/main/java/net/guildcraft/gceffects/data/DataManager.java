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
        instance.getFileManager().getDataFile().set(uuid + ".BLOOD_EFFECT", true);
        instance.getFileManager().getDataFile().set(uuid + ".TOGGLE_STATUS", true);
        instance.getFileManager().saveDataFile();
    }
    public String getActiveEffect(UUID uuid) {
        return instance.getFileManager().getDataFile().getString(uuid + ".ACTIVE_EFFECT");
    }
    public boolean getToggleStatus(UUID uuid) {
        return instance.getFileManager().getDataFile().getBoolean(uuid + ".TOGGLE_STATUS");
    }
    public boolean getBloodEffectStatus(UUID uuid) {
        return instance.getFileManager().getDataFile().getBoolean(uuid + ".BLOOD_EFFECT");
    }
    public void setActiveEffect(UUID uuid, String effect) {
        instance.getFileManager().getDataFile().set(uuid + ".ACTIVE_EFFECT", effect.toUpperCase());
        instance.getFileManager().saveDataFile();
    }
    public void setToggleStatus(UUID uuid, boolean status) {
        instance.getFileManager().getDataFile().set(uuid + ".TOGGLE_STATUS", status);
        instance.getFileManager().saveDataFile();
    }
    public void setBloodEffect(UUID uuid, boolean status) {
        instance.getFileManager().getDataFile().set(uuid + ".BLOOD_EFFECT", status);
        instance.getFileManager().saveDataFile();
    }
    public boolean playerExists(UUID uuid) {
        return instance.getFileManager().getDataFile().isConfigurationSection(String.valueOf(uuid));
    }
}
