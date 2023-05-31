package net.guildcraft.gceffects.effect.effects;

import net.guildcraft.gceffects.effect.KillEffect;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ExplosionEffect extends KillEffect {
    @Override
    public String getName() {
        return "Explosion";
    }

    @Override
    public void performEffect(Player p, Player p1) {
        Location loc = p.getLocation();
        final PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.EXPLOSION_LARGE, true,
                (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), 7.0f, 7.0f, 7.0f, 13.0f, 80, (int[])null);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);

        if(p1 != null) {
            ((CraftPlayer)p1).getHandle().playerConnection.sendPacket(packet);
        }

    }
}
