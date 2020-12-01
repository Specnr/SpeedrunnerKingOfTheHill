package me.specnr.manhuntkoth.helpers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class HunterHelper {
    public static Set<UUID> Runners = new HashSet<>();
    public static Location LatestRunnerPortal = null;

    public static void giveCompass(Player p) {
        p.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
    }

    public static void giveAllHuntersACompass() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!Runners.contains(p.getUniqueId())) {
                giveCompass(p);
            }
        }
    }

    /**
     * Removes a runner from the list
     * @param uid
     */
    public static void removeRunner(UUID uid) {
        Runners.remove(uid);
    }

    /**
     * Removes a runner from the list
     * @param uid
     */
    public static void addRunner(UUID uid) {
        Runners.add(uid);
    }

    /**
     * Picks a random hunter to become the runner.
     * @return Player to be the runner
     */
    public static UUID getRandomRunner() {
        ArrayList<UUID> options = new ArrayList<>();
        Random r = new Random();
        // Add all the non-runners to a list
        for (Player p : Bukkit.getOnlinePlayers()) {
            UUID curr = p.getUniqueId();
            if (!Runners.contains(curr)) {
                options.add(curr);
            }
        }
        // Pick a random one
        return options.get(r.nextInt(options.size()));
    }
}
