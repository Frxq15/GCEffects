package net.guildcraft.gceffects.command;

import net.guildcraft.gceffects.GCEffects;
import net.guildcraft.gceffects.data.GCPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setKillEffectCommand implements CommandExecutor {
    private final GCEffects instance = GCEffects.getInstance();
    @Override
    public boolean onCommand(CommandSender p, Command command, String s, String[] args) {
        if(!p.hasPermission("gceffects.setkilleffect")) {
            p.sendMessage(instance.formatMsg("NO_PERMISSION"));
            return true;
        }
        if(args.length == 1) {
            if(!(p instanceof Player)) {
                instance.log("This command cannot be executed from console.");
                return true;
            }
            Player player = (Player) p;
            GCPlayer gcPlayer = GCPlayer.getPlayerData(instance, player.getUniqueId());
            String effect = args[0];
            if(instance.getEffectsRegistry().getDisabledEffects().contains(effect.toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_DISABLED")
                        .replace("%effect%", effect.toUpperCase()));
                return true;
            }
            if(!instance.getEffectsRegistry().getEffects().contains(effect.toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_NOT_FOUND")
                        .replace("%effect%", effect.toUpperCase()));
                return true;
            }
            if(!p.hasPermission("gceffects.killeffect."+effect.toLowerCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_LOCKED")
                        .replace("%effect%", args[1]));
            }
            if(gcPlayer.getActiveEffect().equals(effect.toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_ALREADY_ACTIVE")
                        .replace("%effect%", effect.toUpperCase()));
                return true;
            }
            gcPlayer.setActiveEffect(effect.toUpperCase());
            p.sendMessage(instance.formatMsg("KILL_EFFECT_SET")
                    .replace("%effect%", effect.toUpperCase()));
            return true;
        }
        if(args.length == 2) {
            if(Bukkit.getPlayer(args[1]) == null) {
                p.sendMessage(instance.formatMsg("TARGET_NOT_FOUND"));
                return true;
            }
            Player target = Bukkit.getPlayer(args[1]);
            GCPlayer gcPlayer = GCPlayer.getPlayerData(instance, target.getUniqueId());
            String effect = args[0];
            if(instance.getEffectsRegistry().getDisabledEffects().contains(effect.toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_DISABLED")
                        .replace("%effect%", effect.toUpperCase()));
                return true;
            }
            if(!instance.getEffectsRegistry().getEffects().contains(effect.toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_NOT_FOUND")
                        .replace("%effect%", effect.toUpperCase()));
                return true;
            }
            if(gcPlayer.getActiveEffect().equals(effect.toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_ALREADY_ACTIVE_OTHER")
                        .replace("%effect%", effect.toUpperCase())
                        .replace("%player%", target.getName()));
                return true;
            }
            gcPlayer.setActiveEffect(effect.toUpperCase());
            p.sendMessage(instance.formatMsg("KILL_EFFECT_SET_OTHER")
                    .replace("%effect%", effect.toUpperCase())
                    .replace("%player%", target.getName()));
            return true;
        }
        if(!p.hasPermission("gceffects.setkilleffect.others")) {
            p.sendMessage(instance.colourize("&cUsage: /setkilleffect <effect>"));
            return true;
        }
        p.sendMessage(instance.colourize("&cUsage: /setkilleffect <effect> <player>"));
        return true;
    }
}
