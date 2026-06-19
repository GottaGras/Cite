package net.gottagras.Market;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MarketInventory {

    public boolean compareInventory(Inventory inv1, Inventory inv2) {
        if (inv1 == null || inv2 == null) return false;
        if (inv1.getSize() != inv2.getSize()) return false;

        for (int i = 0; i < inv1.getSize(); i++) {
            ItemStack item1 = inv1.getItem(i);
            ItemStack item2 = inv2.getItem(i);

            if (item1 == null && item2 != null) return false;
            if (item1 != null && item2 == null) return false;

            if (item1 == null && item2 == null) continue;

            Material mat1 = item1.getType();
            Material mat2 = item2.getType();

            if (mat1 != mat2) return false;
        }

        return true;
    }

    public ItemStack createItem(Material mat, double price, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();

        Component displayName = Component.text(ChatColor.GOLD + name);
        Component lore = Component.text(ChatColor.GRAY + "Price: " + price);
        List<Component> loreList = new ArrayList<>();
        loreList.add(lore);

        meta.displayName(displayName);
        meta.lore(loreList);
        item.setItemMeta(meta);

        return item;
    }

    public Inventory categoryInventory(MarketManager mng, MarketCategory cat) {
        Set<Material> materials = mng.getMaterials(cat);

        int rows = materials.size() / 7 + 1;
        int size = 18 + rows * 9;

        Component title = Component.text(
            ChatColor.DARK_GRAY + "Market - " + cat.name()
        );

        Inventory inventory = Bukkit.createInventory(null, size, title);

        int index = 0;
        for (Material material : materials) {
            double price = mng.calculateCurrentPrice(material);
            ItemStack item = createItem(material, price, material.name());

            int row = index / 7 + 1;
            int col = (index % 7) + 1;

            int realIdx = row * 9 + col;
            inventory.setItem(realIdx, item);

            index++;
        }

        return inventory;
    }

    public Inventory sellInventory(
        MarketManager mng,
        MarketCategory cat,
        Material mat
    ) {
        return Bukkit.createInventory(
            null,
            9 * 3,
            Component.text(ChatColor.DARK_GRAY + "Sell - " + mat.name())
        );
    }
}
