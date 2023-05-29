package net.guildcraft.gceffects.effect.effects;

import net.guildcraft.gceffects.effect.KillEffect;
import org.bukkit.entity.Player;

public class LightningEffect extends KillEffect {
    @Override
    public String getName() {
        return "Lightning";
    }

    @Override
    public void performEffect(Player p) {
        int i = 0;
        while(i < 2) {
            p.getWorld().strikeLightning(p.getLocation());
            i++;
        }
    }
}
