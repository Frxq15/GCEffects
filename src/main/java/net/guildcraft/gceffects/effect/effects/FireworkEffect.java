package net.guildcraft.gceffects.effect.effects;

import net.guildcraft.gceffects.effect.KillEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;

public class FireworkEffect extends KillEffect {
    @Override
    public String getName() {
        return "Firework";
    }

    @Override
    public void performEffect(Player p, Player p1) {
        Location loc = p.getLocation();
        p.getWorld().spawn(loc, Firework.class);
    }
}
