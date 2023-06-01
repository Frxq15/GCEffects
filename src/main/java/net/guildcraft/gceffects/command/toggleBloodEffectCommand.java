package net.guildcraft.gceffects.command;

import net.guildcraft.gceffects.GCEffects;
import net.guildcraft.gceffects.data.GCPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class toggleBloodEffectCommand implements CommandExecutor {
    private final GCEffects instance = GCEffects.getInstance();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player)) {
            instance.log("This command cannot be executed from console.");
            return true;
        }
        Player p = (Player)commandSender;
        if(!p.hasPermission("gceffects.togglebloodeffects")) {
            p.sendMessage(instance.formatMsg("NO_PERMISSION"));
            return true;
        }
        if(args.length == 0) {
            GCPlayer gcPlayer = GCPlayer.getPlayerData(instance, p.getUniqueId());
            if(gcPlayer.getBloodEffectStatus()) {
                gcPlayer.setBloodEffectStatus(false);
                p.sendMessage(instance.formatMsg("BLOOD_EFFECTS_DISABLED"));
                return true;
            }
            gcPlayer.setBloodEffectStatus(true);
            p.sendMessage(instance.formatMsg("BLOOD_EFFECTS_ENABLED"));
            return true;
        }
        if(args.length == 1) {
            if(!p.hasPermission("gceffects.togglebloodeffects.others")) {
                p.sendMessage(instance.formatMsg("NO_PERMISSION"));
                return true;
            }
            if(Bukkit.getPlayer(args[0]) == null) {
                p.sendMessage(instance.formatMsg("TARGET_NOT_FOUND"));
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            GCPlayer gcPlayer = GCPlayer.getPlayerData(instance, target.getUniqueId());
            if(gcPlayer.getBloodEffectStatus()) {
                gcPlayer.setBloodEffectStatus(false);
                p.sendMessage(instance.formatMsg("BLOOD_EFFECTS_DISABLED_OTHER")
                        .replace("%player%", target.getName()));
                return true;
            }
            gcPlayer.setBloodEffectStatus(true);
            p.sendMessage(instance.formatMsg("BLOOD_EFFECTS_ENABLED_OTHER")
                    .replace("%player%", target.getName()));
            return true;
        }
        if(!p.hasPermission("gceffects.togglebloodeffects.others")) {
            p.sendMessage(instance.colourize("&cUsage: /togglebloodeffects"));
            return true;
        }
        p.sendMessage(instance.colourize("&cUsage: /togglebloodeffects <player>"));
        return false;
    }
}
