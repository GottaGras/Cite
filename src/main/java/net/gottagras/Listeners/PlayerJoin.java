package net.gottagras.Listeners;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Component joinMessage = Component.text(
            ChatColor.GREEN +
                event.getPlayer().getName() +
                " has joined the server!"
        );
        event.joinMessage(joinMessage);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Component quitMessage = Component.text(
            ChatColor.RED +
                event.getPlayer().getName() +
                " has left the server!"
        );

        event.quitMessage(quitMessage);
    }
}
