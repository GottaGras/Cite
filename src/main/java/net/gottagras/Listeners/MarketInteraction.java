package net.gottagras.Listeners;

import net.gottagras.Cite;
import net.gottagras.Market.MarketCategory;
import net.gottagras.Market.MarketInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MarketInteraction implements Listener {

    private final Cite plugin;

    public MarketInteraction(Cite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMarketInteraction(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory == null) return;

        MarketInventory marketInventory = new MarketInventory();
        for (MarketCategory cat : MarketCategory.values()) {
            if (
                marketInventory.compareInventory(
                    inventory,
                    marketInventory.categoryInventory(plugin.marketManager, cat)
                )
            ) {
                event.setCancelled(true);

                plugin
                    .getLogger()
                    .info(
                        "Item " +
                            event.getCurrentItem() +
                            " clicked in market inventory."
                    );

                break;
            }
        }
    }
}
