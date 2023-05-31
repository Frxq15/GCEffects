package net.guildcraft.gceffects.command;

import net.guildcraft.gceffects.GCEffects;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class testEffectCommand implements CommandExecutor {
    private final GCEffects instance = GCEffects.getInstance();
    @Override
    public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
        if(!p.hasPermission("gceffects.testeffect")) {
            p.sendMessage(instance.formatMsg("NO_PERMISSION"));
            return true;
        }
        if(args.length == 2) {
            if(Bukkit.getPlayer(args[0]) == null) {
                p.sendMessage(instance.formatMsg("TARGET_NOT_FOUND"));
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if(!instance.getEffectsRegistry().getEffects().contains(args[1].toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_NOT_FOUND")
                        .replace("%effect%", args[1]));
                return true;
            }
            instance.getEffectsRegistry().executeEffect(target, null, args[1]);
            p.sendMessage(instance.formatMsg("SENDING_TEST_EFFECT")
                    .replace("%effect%", args[1].toUpperCase())
                    .replace("%target%", target.getName()));
            return true;
        }
        p.sendMessage(instance.colourize("&cUsage: /testeffect <player> <effect>"));
        return true;
    }
}
