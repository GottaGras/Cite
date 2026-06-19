package gottagras;

import static org.junit.jupiter.api.Assertions.assertTrue;

import net.gottagras.Market.MarketManager;
import org.bukkit.Material;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private void printPrice(MarketManager market) {
        for (Material material : market.getMaterials()) {
            double price = market.calculateCurrentPrice(material);
            System.out.println(material + ": " + Math.round(price));
        }
    }

    @Test
    public void marketTest() {
        MarketManager market = new MarketManager();
        printPrice(market);

        assertTrue(true);
    }
}
