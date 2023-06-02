package net.guildcraft.gceffects.effect.effects.bloodEffects;

import net.guildcraft.gceffects.effect.KillEffect;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TNTBloodEffect extends KillEffect {
    @Override
    public String getName() {
        return "TNT";
    }

    @Override
    public void performEffect(Player p, Player p1) {
        p.playEffect(p1.getLocation(), Effect.STEP_SOUND, Material.TNT);
    }
}
