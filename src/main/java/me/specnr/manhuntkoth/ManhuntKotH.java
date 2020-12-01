package me.specnr.manhuntkoth;

import me.specnr.manhuntkoth.commands.KotHReset;
import me.specnr.manhuntkoth.commands.KotHStartGiven;
import me.specnr.manhuntkoth.commands.KotHStartRandom;
import me.specnr.manhuntkoth.commands.GetRunners;
import me.specnr.manhuntkoth.events.OnGeneralInteraction;
import me.specnr.manhuntkoth.events.OnHunterInteraction;
import me.specnr.manhuntkoth.events.OnRunnerInteraction;
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
        System.out.println("Minecraft King of the Hill Loaded");
    }
}
