package net.guildcraft.gceffects.effect.effects;

import net.guildcraft.gceffects.GCEffects;
import net.guildcraft.gceffects.effect.KillEffect;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class testEffect extends KillEffect {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public void performEffect(Player p, Player p1) {
        Location loc = p.getLocation();
        new BukkitRunnable() {
            double phi = 0.0;
            public void run() {
                this.phi += 0.39269908169872414;
                for (double t = 0.0; t <= 6.283185307179586; t += 0.19634954084936207) {
                    for (double i = 0.0; i < 1.0; ++i) {
                        final double x = 0.3 * (6.283185307179586 - t) * 0.5 * Math.cos(t + this.phi + i * 3.141592653589793);
                        final double y = 0.5 * t;
                        final double z = 0.3 * (6.283185307179586 - t) * 0.5 * Math.sin(t + this.phi + i * 3.141592653589793);
                        loc.add(x, y, z);
                        p.playEffect(loc, Effect.PORTAL, 1);
                        loc.subtract(x, y, z);
                    }
                }
                if (this.phi > 31.41592653589793) {
                    this.cancel();
                }
            }
        }.runTaskTimer(GCEffects.getInstance(), 0L, 1L);
    }
}
