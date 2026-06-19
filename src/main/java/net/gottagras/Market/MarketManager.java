package net.gottagras.Market;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.Material;

public class MarketManager {

    private final Map<Material, MarketItem> items = new HashMap<>();
    private final double lambda = 0.0005;

    public MarketManager() {
        registerItem(Material.DIAMOND, MarketCategory.MINERAIS, 500.0);
        registerItem(Material.GOLD_INGOT, MarketCategory.MINERAIS, 150.0);
        registerItem(Material.WHEAT, MarketCategory.CULTURES, 5.0);
        registerItem(Material.CARROT, MarketCategory.CULTURES, 4.0);
    }

    private void registerItem(
        Material mat,
        MarketCategory cat,
        double basePrice
    ) {
        items.put(mat, new MarketItem(mat, cat, basePrice));
    }

    public Set<Material> getMaterials() {
        return items.keySet();
    }

    public Set<Material> getMaterials(MarketCategory cat) {
        Set<Material> materials = new HashSet<>();
        for (MarketItem item : items.values()) {
            if (item.getCategory() == cat) {
                materials.add(item.getMaterial());
            }
        }
        return materials;
    }

    public double getIntraCategoryMultiplier(MarketItem targetItem) {
        double totalCategoryVolume = 0;
        int itemsInCategory = 0;

        for (MarketItem item : items.values()) {
            if (item.getCategory() == targetItem.getCategory()) {
                item.applyTimeDecay();
                totalCategoryVolume += item.getVolume();
                itemsInCategory++;
            }
        }

        if (totalCategoryVolume == 0 || itemsInCategory == 0) return 1.0;

        double averageVolume = totalCategoryVolume / itemsInCategory;
        double targetVolume = Math.max(targetItem.getVolume(), 1.0);
        double ratio = averageVolume / targetVolume;

        return Math.max(0.5, Math.min(2.0, ratio));
    }

    public double calculateCurrentPrice(Material material) {
        MarketItem item = items.get(material);
        if (item == null) return 0.0;

        item.applyTimeDecay();

        double priceDemerit = Math.exp(-lambda * item.getVolume());
        // double intraPressure = getIntraCategoryMultiplier(item);

        return item.getBasePrice() * priceDemerit;
    }

    public double sellItem(Material material, int amount) {
        MarketItem item = items.get(material);
        if (item == null || amount <= 0) return 0.0;

        double priceBefore = calculateCurrentPrice(material);
        item.addVolume(amount);
        double priceAfter = calculateCurrentPrice(material);

        return ((priceBefore + priceAfter) / 2.0) * amount;
    }
}
