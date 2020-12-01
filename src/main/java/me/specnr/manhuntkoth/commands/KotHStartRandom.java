package me.specnr.manhuntkoth.commands;

import me.specnr.manhuntkoth.helpers.BroadcastHelper;
import me.specnr.manhuntkoth.helpers.HunterHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class KotHStartRandom implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        HunterHelper.Runners.clear();
        // Add n random runners, or 1 if no args are given
        int numRunners = 1;
        if (args.length == 1) {
            try {
                numRunners = Math.min(Integer.parseInt(args[0]), Bukkit.getOnlinePlayers().size() - 1);
            } catch (NumberFormatException e) {
                sender.sendMessage(BroadcastHelper.BroadcastPrefix + "Error: Argument must be an integer");
                return false;
            }
        }
        for (int i=0; i<numRunners; i++) {
            UUID currRunner = HunterHelper.getRandomRunner();
            HunterHelper.addRunner(currRunner);
        }
        BroadcastHelper.Broadcast("Started KotH Random!");
        BroadcastHelper.BroadcastRunners();
        HunterHelper.giveAllHuntersACompass();
        return false;
    }
}
