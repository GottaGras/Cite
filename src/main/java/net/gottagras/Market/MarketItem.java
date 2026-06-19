package net.gottagras.Market;

import org.bukkit.Material;

public class MarketItem {

    private final Material material;
    private final MarketCategory category;
    private final double basePrice;
    private double volume;
    private long lastUpdateTime;

    public MarketItem(
        Material material,
        MarketCategory category,
        double basePrice
    ) {
        this.material = material;
        this.category = category;
        this.basePrice = basePrice;
        this.volume = 0.0;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public Material getMaterial() {
        return material;
    }

    public MarketCategory getCategory() {
        return category;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getVolume() {
        return volume;
    }

    public void applyTimeDecay() {
        long currentTime = System.currentTimeMillis();
        long timeElapsed = currentTime - lastUpdateTime;
        double hoursElapsed = timeElapsed / 3600000.0;

        if (hoursElapsed > 0) {
            double decayRate = 0.02;
            this.volume = this.volume * Math.pow(1.0 - decayRate, hoursElapsed);

            if (this.volume < 1.0) this.volume = 0.0;

            this.lastUpdateTime = currentTime;
        }
    }

    public void addVolume(int amount) {
        applyTimeDecay();
        this.volume += amount;
    }
}
