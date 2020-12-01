package me.specnr.manhuntkoth.commands;

import me.specnr.manhuntkoth.helpers.BroadcastHelper;
import me.specnr.manhuntkoth.helpers.HunterHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class KotHStartGiven implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Add given player names to runners
        if (args.length > 0) {
            HunterHelper.Runners.clear();
            ArrayList<Player> runners = new ArrayList<>();
            // Verify all names are valid
            for (String name : args) {
                Player curr = Bukkit.getPlayer(name);
                if (curr == null) {
                    sender.sendMessage(BroadcastHelper.BroadcastPrefix + "Error: " + name + " is not a valid name");
                    return false;
                }
                runners.add(curr);
            }
            for (Player p : runners) {
                HunterHelper.addRunner(p.getUniqueId());
            }
        } else {
            sender.sendMessage(BroadcastHelper.BroadcastPrefix + "Error: Must provide at least 1 name");
            return false;
        }
        BroadcastHelper.Broadcast("Started KotH Normal!");
        BroadcastHelper.BroadcastRunners();
        HunterHelper.giveAllHuntersACompass();
        return true;
    }
}
