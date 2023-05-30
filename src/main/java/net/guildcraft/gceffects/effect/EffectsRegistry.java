package net.guildcraft.gceffects.effect;

import net.guildcraft.gceffects.effect.effects.LightningEffect;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EffectsRegistry {
    private final List<String> killEffects = new ArrayList<String>();

    public void registerEffects() {
        Arrays.stream(Effects.values()).forEach(effects -> {
            killEffects.add(effects.name().toLowerCase());
        });
    }
    public List<String> getEffects() { return killEffects; }


    public void executeEffect(Player player, Effects effect) {
        switch (effect) {
            case NONE:
                return;
            case LIGHTNING:
                new LightningEffect().performEffect(player);
                return;
            default:
                return;
        }
    }
}
