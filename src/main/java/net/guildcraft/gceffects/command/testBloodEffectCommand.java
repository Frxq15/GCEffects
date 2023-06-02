package net.guildcraft.gceffects.command;

import net.guildcraft.gceffects.GCEffects;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class testBloodEffectCommand implements CommandExecutor {
    private final GCEffects instance = GCEffects.getInstance();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player)) {
            instance.log("This command cannot be executed from console.");
            return true;
        }
        Player p = (Player)commandSender;
        if(!p.hasPermission("gceffects.testbloodeffect")) {
            p.sendMessage(instance.formatMsg("NO_PERMISSION"));
            return true;
        }
        if(args.length == 2) {
            if(Bukkit.getPlayer(args[0]) == null) {
                p.sendMessage(instance.formatMsg("TARGET_NOT_FOUND"));
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if(instance.getEffectsRegistry().getDisabledBloodEffects().contains(args[1].toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_DISABLED")
                        .replace("%effect%", args[1]));
                return true;
            }
            if(!instance.getEffectsRegistry().getBloodEffects().contains(args[1].toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_NOT_FOUND")
                        .replace("%effect%", args[1]));
                return true;
            }
            instance.getEffectsRegistry().executeBloodEffect(p, target, args[1]);
            p.sendMessage(instance.formatMsg("SENDING_TEST_BLOOD_EFFECT")
                    .replace("%effect%", args[1].toUpperCase())
                    .replace("%target%", target.getName()));
            return true;
        }
        p.sendMessage(instance.colourize("&cUsage: /testbloodeffect <player> <effect>"));
        return true;
    }
}
