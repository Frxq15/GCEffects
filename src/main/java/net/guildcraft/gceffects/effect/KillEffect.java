package net.guildcraft.gceffects.effect;

import com.avaje.ebean.validation.NotNull;
import org.bukkit.entity.Player;

public abstract class KillEffect {

    public KillEffect() {}

    public abstract String getName();

    public abstract void performEffect(Player p);


}
