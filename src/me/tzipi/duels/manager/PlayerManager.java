package me.tzipi.duels.manager;


import me.tzipi.duels.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerManager {
    private Main plugin;
     private final GameManager gameManager;
    public PlayerManager(GameManager gameManager) {
        this.gameManager = gameManager;

    }
    public void giveKits() {
      Bukkit.getOnlinePlayers().stream().filter(player -> this.gameManager.inGame.contains(player)).forEach(this::giveKit);
    }
    public void giveKit(Player player) {
        player.getInventory().clear();
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        player.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
        player.getInventory().addItem(new ItemStack(Material.BOW));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 5));
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.IRON_BOOTS,1);
        armor[1] = new ItemStack(Material.IRON_LEGGINGS,1 );
        armor[2] = new ItemStack(Material.IRON_CHESTPLATE,1 );
        armor[3] = new ItemStack(Material.IRON_HELMET,1);

        ItemMeta metaBoots = armor[0].getItemMeta();
        metaBoots.addEnchant(Enchantment.DURABILITY,1,true);
        armor[0].setItemMeta(metaBoots);

        ItemMeta metaLegs = armor[1].getItemMeta();
        metaLegs.addEnchant(Enchantment.DURABILITY,1,true);
        armor[1].setItemMeta(metaLegs);

        ItemMeta metaChest = armor[2].getItemMeta();
        metaChest.addEnchant(Enchantment.DURABILITY,1,true);
        armor[2].setItemMeta(metaChest);

        ItemMeta metaHelm = armor[3].getItemMeta();
        metaHelm.addEnchant(Enchantment.DURABILITY,1,true);
        armor[3].setItemMeta(metaHelm);


        player.getInventory().setArmorContents(armor);


    }
    }

