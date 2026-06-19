package net.gottagras.Commands;

import com.destroystokyo.paper.profile.PlayerProfile;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.gottagras.Cite;
import net.gottagras.Market.MarketCategory;
import net.gottagras.Market.MarketInventory;
import net.gottagras.Market.MarketManager;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;

public class Test implements CommandExecutor {

    private final Cite plugin;

    public Test(Cite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
        CommandSender sender,
        Command cmd,
        String label,
        String[] args
    ) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        MarketManager mng = plugin.marketManager;
        MarketCategory cat = MarketCategory.MINERAIS;
        MarketInventory marketInventory = new MarketInventory();

        Inventory inventory = marketInventory.categoryInventory(mng, cat);

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);

        PlayerProfile profile = Bukkit.createProfile("");
        PlayerTextures textures;
        URL url;

        HashMap<String, String> textureUrls = new HashMap<>();
        textureUrls.put(
            "http://textures.minecraft.net/texture/77b733e0ba964e8157476f3353426a87ceab3dbc3fb4bddaa2d57886df37ba6",
            ChatColor.GREEN + " + 1 Stack"
        );
        textureUrls.put(
            "http://textures.minecraft.net/texture/61d0f82a2a4cdd85f79f4d9d9798f9c3a5bccbe9c7f2e27c5fc836651a8f3f45",
            ChatColor.GREEN + " + 1 Item"
        );
        textureUrls.put(
            "http://textures.minecraft.net/texture/edf5c2f893bd3f89ca40703ded3e42dd0fbdba6f6768c8789afdff1fa78bf6",
            ChatColor.RED + " - 1 Item"
        );
        textureUrls.put(
            "http://textures.minecraft.net/texture/c49d271c5df84f8a3c8aa5d15427f62839341dab52c619a5987d38fbe18e464",
            ChatColor.RED + " - 1 Stack"
        );

        for (Map.Entry<String, String> entry : textureUrls.entrySet()) {
            try {
                url = new URI(entry.getKey()).toURL();

                textures = profile.getTextures();
                textures.setSkin(url);
                profile.setTextures(textures);

                SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
                skullMeta.setPlayerProfile(profile);

                Component displayName = Component.text(entry.getValue());
                skullMeta.displayName(displayName);

                item.setItemMeta(skullMeta);
            } catch (Exception e) {
                e.printStackTrace();
            }

            player.getInventory().addItem(item);
        }

        player.openInventory(inventory);
        return true;
    }
}
