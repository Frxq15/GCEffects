package net.guildcraft.gceffects.effect;

import net.guildcraft.gceffects.GCEffects;
import net.guildcraft.gceffects.effect.effects.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EffectsRegistry {
    private final List<String> killEffects = new ArrayList<String>();
    private final GCEffects instance = GCEffects.getInstance();

    public void registerEffects() {
        instance.getConfig().getConfigurationSection("ENABLED_EFFECTS").getKeys(false).forEach(effect -> {
            if(instance.getConfig().getBoolean("ENABLED_EFFECTS."+effect)) {
                killEffects.add(effect.toUpperCase());
            }
        });
    }
    public List<String> getEffects() { return killEffects; }

    public void executeEffect(Player player, Player p1, String effect) {
        if(!player.isOnline()) {
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
}
