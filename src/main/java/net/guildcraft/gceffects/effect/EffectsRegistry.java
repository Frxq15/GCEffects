package net.guildcraft.gceffects.effect;

import net.guildcraft.gceffects.GCEffects;
import net.guildcraft.gceffects.effect.effects.*;
import net.guildcraft.gceffects.effect.effects.bloodEffects.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EffectsRegistry {
    private final List<String> killEffects = new ArrayList<String>();
    private final List<String> killEffectsDisabled = new ArrayList<String>();
    private final List<String> bloodEffects = new ArrayList<String>();
    private final List<String> bloodEffectsDisabled = new ArrayList<String>();
    private final GCEffects instance = GCEffects.getInstance();

    public void registerEffects() {
        instance.getConfig().getConfigurationSection("ENABLED_EFFECTS").getKeys(false).forEach(effect -> {
            if(instance.getConfig().getBoolean("ENABLED_EFFECTS."+effect)) {
                killEffects.add(effect.toUpperCase());
            }
            if(!instance.getConfig().getBoolean("ENABLED_EFFECTS."+effect)) {
                killEffectsDisabled.add(effect.toUpperCase());
            }
        });
        instance.getConfig().getConfigurationSection("ENABLED_BLOOD_EFFECTS").getKeys(false).forEach(effect -> {
            if(instance.getConfig().getBoolean("ENABLED_BLOOD_EFFECTS."+effect)) {
                bloodEffects.add(effect.toUpperCase());
            }
            if(!instance.getConfig().getBoolean("ENABLED_BLOOD_EFFECTS."+effect)) {
                bloodEffectsDisabled.add(effect.toUpperCase());
            }
        });
    }
    public List<String> getEffects() { return killEffects; }
    public List<String> getDisabledEffects() { return killEffectsDisabled; }
    public List<String> getBloodEffects() { return bloodEffects; }
    public List<String> getDisabledBloodEffects() { return bloodEffectsDisabled; }

    public void executeEffect(Player player, Player p1, String effect) {
        if(!player.isOnline()) {
            return;
        }
        if (getDisabledEffects().contains(effect.toUpperCase())) {
             return;
        }
        switch (effect.toUpperCase()) {
            case "NONE":
                return;
            case "TEST":
                new testEffect().performEffect(player, null);
                return;
            case "LIGHTNING":
                new LightningEffect().performEffect(player, null);
                return;
            case "EXPLOSION":
                new ExplosionEffect().performEffect(player, null);
            case "FIREWORK":
                new FireworkEffect().performEffect(player, null);
            case "FLAME":
                new FlameEffect().performEffect(player, null);
            default:
        }
    }
    public void executeBloodEffect(Player player, Player p1, String effect) {
        if(!player.isOnline()) {
            return;
        }
        if(getDisabledBloodEffects().contains(effect.toUpperCase())) {
            return;
        }
        switch (effect.toUpperCase()) {
            case "NONE":
                return;
            case "TEST":
                new BleedingTestEffect().performEffect(player, p1);
            case "BLEEDING_RED":
                new BleedingRedEffect().performEffect(player, p1);
            case "BLEEDING_BLUE":
                new BleedingBlueEffect().performEffect(player, p1);
            case "BLEEDING_YELLOW":
                new BleedingYellowEffect().performEffect(player, p1);
            case "FLAME_BLOOD":
                new FlameBloodEffect().performEffect(player, p1);
            default:
        }
    }
}
