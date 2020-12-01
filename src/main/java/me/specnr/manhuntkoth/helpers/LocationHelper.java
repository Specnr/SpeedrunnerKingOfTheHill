package me.specnr.manhuntkoth.helpers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.CompassMeta;

import java.util.ArrayList;
import java.util.UUID;

public class LocationHelper {
    /**
     * Updates the Player's compass to track the given Location
     * reference: https://github.com/yummypasta/MinecraftManhunt/blob/master/MinecraftManhunt/src/main/java/com/yoonicode/minecraftmanhunt/PluginCommands.java
     * @param p The player to update compass
     * @param l The location to update to
     */
    public static void updateCompassWithDim (Player p, Location l) {
        switch (p.getWorld().getEnvironment()) {
            case NORMAL:
                p.setCompassTarget(l);
            default:
                PlayerInventory inv = p.getInventory();
                ItemStack compass = null;
                for (int j = 0; j < inv.getSize(); j++) {
                    ItemStack stack = inv.getItem(j);
                    if (stack != null && stack.getType() == Material.COMPASS) {
                        compass = stack;
                        break;
                    }
                }
                CompassMeta meta = (CompassMeta) compass.getItemMeta();
                meta.setLodestone(l);
                meta.setLodestoneTracked(false);
                compass.setItemMeta(meta);
        }
    }

    public static void updateHunterCompass (Player p) {
        Location newLocation = p.getLocation();
        ArrayList<Player> sameDimRunners = new ArrayList<>();
        for (UUID runnerId : HunterHelper.Runners) {
            Player runner = Bukkit.getPlayer(runnerId);
            if (runner.getWorld().getEnvironment() == p.getWorld().getEnvironment()) {
                sameDimRunners.add(runner);
            }
        }
        if (sameDimRunners.size() == 0) {
            // Set newLocation to latest portal
            if (HunterHelper.LatestRunnerPortal != null) {
                newLocation = HunterHelper.LatestRunnerPortal;
            } else {
                p.sendMessage(BroadcastHelper.BroadcastPrefix + " Portal location was lost");
            }
        } else {
            Player closestPlayer = sameDimRunners.get(0);
            sameDimRunners.remove(0);
            double minDist = closestPlayer.getLocation().distance(newLocation);
            for (Player runner : sameDimRunners) {
                // Get the distance between runner and hunter
                double newDist = runner.getLocation().distance(newLocation);
                if (newDist < minDist) {
                    closestPlayer = runner;
                    minDist = newDist;
                }
            }
            newLocation = closestPlayer.getLocation();
        }
        updateCompassWithDim(p, newLocation);
    }
}
