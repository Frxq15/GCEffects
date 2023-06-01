package net.guildcraft.gceffects.effect;

import org.bukkit.entity.Player;

public abstract class BloodEffect {

    public BloodEffect() {}

    public abstract String getName();

    public abstract void performEffect(Player p, Player p1);
}
