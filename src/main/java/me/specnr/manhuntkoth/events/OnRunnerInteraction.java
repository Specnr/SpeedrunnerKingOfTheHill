package me.specnr.manhuntkoth.events;

import me.specnr.manhuntkoth.helpers.HunterHelper;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class OnRunnerInteraction implements Listener {
    @EventHandler
    /**
     * Sets the latest portal location when a runner changes dimensions
     */
    public void onPlayerEnterPortal(PlayerTeleportEvent evt){
        if (HunterHelper.Runners.contains(evt.getPlayer().getUniqueId())) {
            if (evt.getTo().getWorld().getEnvironment() == World.Environment.THE_END) {
                HunterHelper.LatestRunnerEndPortal = evt.getFrom();
            } else {
                HunterHelper.LatestRunnerPortal = evt.getFrom();
            }
            System.out.println(evt.getFrom().getWorld().getEnvironment());
            System.out.println(evt.getTo().getWorld().getEnvironment());
        }
    }
}
