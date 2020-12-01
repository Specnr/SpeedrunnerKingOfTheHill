package me.specnr.manhuntkoth.events;

import me.specnr.manhuntkoth.helpers.HunterHelper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class OnRunnerInteraction implements Listener {
    @EventHandler
    /**
     * Sets the latest portal location when a runner changes dimensions
     */
    public void onPlayerEnterPortal(PlayerPortalEvent evt){
        if (HunterHelper.Runners.contains(evt.getPlayer().getUniqueId())) {
            HunterHelper.LatestRunnerPortal = evt.getFrom();
        }
    }
}
