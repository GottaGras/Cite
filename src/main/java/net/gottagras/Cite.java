package net.gottagras;

import net.gottagras.Commands.Test;
import net.gottagras.Database.Database;
import net.gottagras.Listeners.MarketInteraction;
import net.gottagras.Listeners.PlayerJoin;
import net.gottagras.Market.MarketManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Cite extends JavaPlugin {

    public MarketManager marketManager;

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

        marketManager = new MarketManager();
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(
            new MarketInteraction(this),
            this
        );
    }

    private void registerCommands() {
        Bukkit.getPluginCommand("test").setExecutor(new Test(this));
    }
}
