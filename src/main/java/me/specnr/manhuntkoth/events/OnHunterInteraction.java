package me.specnr.manhuntkoth.events;

import me.specnr.manhuntkoth.helpers.HunterHelper;
import me.specnr.manhuntkoth.helpers.LocationHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class OnHunterInteraction implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent evt){
        Player player = evt.getPlayer();
        if(player.getEquipment().getItemInMainHand().getType() == Material.COMPASS){
            LocationHelper.updateHunterCompass(player);
        }
    }

    @EventHandler
    /**
     * Gives a Compass to respawned Hunters
     */
    public void onPlayerRespawn(PlayerRespawnEvent evt){
        if(HunterHelper.Runners.size() > 0 && !HunterHelper.Runners.contains(evt.getPlayer().getUniqueId())){
            HunterHelper.giveCompass(evt.getPlayer());
        }
    }
}
