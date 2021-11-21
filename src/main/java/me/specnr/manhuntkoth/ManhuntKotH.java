package me.specnr.manhuntkoth;

import me.specnr.manhuntkoth.commands.KotHReset;
import me.specnr.manhuntkoth.commands.KotHStartGiven;
import me.specnr.manhuntkoth.commands.KotHStartRandom;
import me.specnr.manhuntkoth.commands.GetRunners;
import me.specnr.manhuntkoth.events.OnGeneralInteraction;
import me.specnr.manhuntkoth.events.OnHunterInteraction;
import me.specnr.manhuntkoth.events.OnRunnerInteraction;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.java.JavaPlugin;

public final class ManhuntKotH extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new OnGeneralInteraction(), this);
        getServer().getPluginManager().registerEvents(new OnHunterInteraction(), this);
        getServer().getPluginManager().registerEvents(new OnRunnerInteraction(), this);
        getCommand("koth").setExecutor(new KotHStartGiven());
        getCommand("koth-r").setExecutor(new KotHStartRandom());
        getCommand("koth-reset").setExecutor(new KotHReset());
        getCommand("runners").setExecutor(new GetRunners());
        World world = Bukkit.getWorld("world");
        WorldBorder border = world.getWorldBorder();
        border.setSize(8000);
        border.setCenter(0.0, 0.0);
        World nether = Bukkit.getWorld("world_nether");
        WorldBorder netherBorder = nether.getWorldBorder();
        netherBorder.setSize(1000);
        netherBorder.setCenter(0.0, 0.0);
        System.out.println("Minecraft King of the Hill Loaded");
    }
}
