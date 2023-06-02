package net.guildcraft.gceffects.command;

import net.guildcraft.gceffects.GCEffects;
import net.guildcraft.gceffects.data.GCPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setBloodEffectCommand implements CommandExecutor {
    private final GCEffects instance = GCEffects.getInstance();
    @Override
    public boolean onCommand(CommandSender p, Command command, String s, String[] args) {
        if(!p.hasPermission("gceffects.setbloodeffect")) {
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
            if(instance.getEffectsRegistry().getDisabledBloodEffects().contains(effect.toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_DISABLED")
                        .replace("%effect%", effect.toUpperCase()));
                return true;
            }
            if(!instance.getEffectsRegistry().getBloodEffects().contains(effect.toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_NOT_FOUND")
                        .replace("%effect%", effect.toUpperCase()));
                return true;
            }
            if(!p.hasPermission("gceffects.bloodeffect."+effect.toLowerCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_LOCKED")
                        .replace("%effect%", args[1]));
            }
            if(gcPlayer.getBloodEffect().equals(effect.toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_ALREADY_ACTIVE")
                        .replace("%effect%", effect.toUpperCase()));
                return true;
            }
            gcPlayer.setBloodEffect(effect.toUpperCase());
            p.sendMessage(instance.formatMsg("BLOOD_EFFECT_SET")
                    .replace("%effect%", effect.toUpperCase()));
            return true;
        }
        if(args.length == 2) {
            if(Bukkit.getPlayer(args[0]) == null) {
                p.sendMessage(instance.formatMsg("TARGET_NOT_FOUND"));
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            GCPlayer gcPlayer = GCPlayer.getPlayerData(instance, target.getUniqueId());
            String effect = args[0];
            if(instance.getEffectsRegistry().getDisabledBloodEffects().contains(effect.toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_DISABLED")
                        .replace("%effect%", effect.toUpperCase()));
                return true;
            }
            if(!instance.getEffectsRegistry().getBloodEffects().contains(effect.toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_NOT_FOUND")
                        .replace("%effect%", effect.toUpperCase()));
                return true;
            }
            if(gcPlayer.getBloodEffect().equals(effect.toUpperCase())) {
                p.sendMessage(instance.formatMsg("EFFECT_ALREADY_ACTIVE_OTHER")
                        .replace("%effect%", effect.toUpperCase())
                        .replace("%player%", target.getName()));
                return true;
            }
            gcPlayer.setBloodEffect(effect.toUpperCase());
            p.sendMessage(instance.formatMsg("BLOOD_EFFECT_SET_OTHER")
                    .replace("%effect%", effect.toUpperCase())
                    .replace("%player%", target.getName()));
            return true;
        }
        if(!p.hasPermission("gceffects.setbloodeffect.others")) {
            p.sendMessage(instance.colourize("&cUsage: /setbloodeffect <effect>"));
            return true;
        }
        p.sendMessage(instance.colourize("&cUsage: /setbloodeffect <effect> <player>"));
        return true;
    }
}
