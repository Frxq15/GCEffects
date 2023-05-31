package net.guildcraft.gceffects.effect.effects;

import net.guildcraft.gceffects.effect.KillEffect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ExplosionEffect extends KillEffect {
    @Override
    public String getName() {
        return "Explosion";
    }

    @Override
    public void performEffect(Player p) {
        Location loc = p.getLocation();
      //  final PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.EXPLOSION_LARGE, true,
            //    (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), 7.0f, 7.0f, 7.0f, 13.0f, 80, (int[])null);
      //  ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);

        //need to fix my maven for 1.8
        //latest
    }
}
