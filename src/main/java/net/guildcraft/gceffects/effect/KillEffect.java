package net.guildcraft.gceffects.effect;

import org.bukkit.entity.Player;

public abstract class KillEffect {

    public KillEffect() {}

    public abstract String getName();

    public abstract void performEffect(Player p, Player killer);


}
