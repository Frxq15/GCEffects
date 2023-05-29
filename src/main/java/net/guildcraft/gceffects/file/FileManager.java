package net.guildcraft.gceffects.file;

import net.guildcraft.gceffects.GCEffects;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private GCEffects instance;
    public File DataFile;
    public FileConfiguration DataConfig;

    public FileManager(GCEffects instance) { this.instance = instance; }

    public FileConfiguration getDataFile() {
        return this.DataConfig;
    }

    public void reloadDataFile() {
        DataConfig = YamlConfiguration.loadConfiguration(DataFile);
    }
    public void saveDataFile() {
        try {
            DataConfig.save(DataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createDataFile() {
        DataFile = new File(instance.getDataFolder(), "playerdata.yml");
        if (!DataFile.exists()) {
            DataFile.getParentFile().mkdirs();
            instance.log("playerdata.yml was created successfully");
            instance.saveResource("playerdata.yml", false);
        }
        DataConfig = new YamlConfiguration();
        try {
            DataConfig.load(DataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
