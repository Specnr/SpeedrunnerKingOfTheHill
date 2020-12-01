package me.specnr.manhuntkoth.events;

import me.specnr.manhuntkoth.helpers.BroadcastHelper;
import me.specnr.manhuntkoth.helpers.HunterHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnGeneralInteraction implements Listener {
    @EventHandler()
    /**
     * Updates the runner once the previous has died.
     */
    public void onDeath(PlayerDeathEvent evt) {
        if (Bukkit.getOnlinePlayers().size() > 1) {
            Player deadPlayer = evt.getEntity();
            if (HunterHelper.Runners.contains(deadPlayer.getUniqueId())) {
                Player newRunner = deadPlayer.getKiller();
                // If player died of natural causes make the new one random
                if (newRunner == null) {
                    newRunner = Bukkit.getPlayer(HunterHelper.getRandomRunner());
                }
                if (newRunner != null) {
                    HunterHelper.removeRunner(deadPlayer.getUniqueId());
                    HunterHelper.addRunner(newRunner.getUniqueId());
                    BroadcastHelper.Broadcast("§b§l§o" + newRunner.getName() + "§r became a Speedrunner!");
                }
            }
        }
    }
}
