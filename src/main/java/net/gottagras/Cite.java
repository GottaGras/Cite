package net.gottagras;

import net.gottagras.Commands.Test;
import net.gottagras.Database.Database;
import net.gottagras.Listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Cite extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Cite plugin enabled!");
        registerEvents();
        registerCommands();

        try {
            new Database();
            getLogger().info("Database connection established successfully.");
        } catch (Exception e) {
            getLogger().severe(
                "Failed to connect to the database: " + e.getMessage()
            );
        }
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        getLogger().info("Registered PlayerJoin event listener.");
    }

    private void registerCommands() {
        Bukkit.getPluginCommand("test").setExecutor(new Test());
    }
}
